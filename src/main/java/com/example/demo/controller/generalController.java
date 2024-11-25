package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ano;
import com.example.demo.model.Custo;
import com.example.demo.model.DataMock;
import com.example.demo.model.ExecucaoOrcamentaria;
import com.example.demo.model.FonteOrcamentaria;
import com.example.demo.model.Investimento;
import com.example.demo.model.Objeto;
import com.example.demo.model.PlanoOrcamentario;
import com.example.demo.model.UnidadeOrcamentaria;
import com.example.demo.service.AnoService;
import com.example.demo.service.CustoService;
import com.example.demo.service.ExecucaoOrcamentariaService;
import com.example.demo.service.FonteOrcamentariaService;
import com.example.demo.service.ObjetoService;
import com.example.demo.service.PlanoOrcamentarioService;
import com.example.demo.service.UnidadeOrcamentariaService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("teste")
@RequiredArgsConstructor
public class generalController {

    private final CustoService custoService;
    private final PlanoOrcamentarioService planoOrcamentarioService;
    private final UnidadeOrcamentariaService unidadeOrcamentariaService;
    private final FonteOrcamentariaService fonteOrcamentariaService;
    private final AnoService anoService;
    private final ExecucaoOrcamentariaService execucaoOrcamentariaService;
    private final ObjetoService objetoService;
    
    @GetMapping("")
    public ResponseEntity<String> teste() {

        try{
            File excel = ResourceUtils.getFile("classpath:planilha/Planilha PIP 25 - Para a CGTI.xlsx");

            FileInputStream fis = new FileInputStream(excel);
            List<List<String>> mapValue;
            try (XSSFWorkbook workbook = new XSSFWorkbook(fis)){

                XSSFSheet sheet = workbook.getSheetAt(0);
                
                Logger.getGlobal().info("Extraindo dados da planilha");
                mapValue = SheetToMapList(sheet);

                
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getLocalizedMessage(), e);
                return ResponseEntity.internalServerError().build();
            }

            consumirPlanilha(mapValue);
            salvarDadosNoBanco();
            
            return ResponseEntity.ok("Concluido");
        } catch(FileNotFoundException ex) {
            Logger.getLogger("teste").log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            return ResponseEntity.notFound().build();
        } catch(Exception ex) {
            Logger.getLogger("teste").log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }

        
    }

    private List<List<String>> SheetToMapList(XSSFSheet sheet) {
        ArrayList<List<String>> listRows = new ArrayList<>();
        
        
        for(Row row : sheet){

            ArrayList<String> values = new ArrayList<>();

            row.forEach(cell -> values.add(new DataFormatter().formatCellValue(cell)));
 
            listRows.add(values);
        }

        return listRows;
    }

    private void consumirPlanilha(List<List<String>> mapList) {
        mapList.remove(0); // remover primeiro registro porque é o cabeçalho

        for(List<String> linha : mapList) {
            Logger.getGlobal().info("Consumindo: Plano - " + linha.get(2) + "; Unidade - " + linha.get(0).split(" - ")[1]);

            // preparar plano orcamentario
            Long codPlano = Long.valueOf(linha.get(2));
            String nomePlano = linha.get(3);
            PlanoOrcamentario plano = planoOrcamentarioService.findOrCreate(codPlano, nomePlano);

            // prepara unidade Orçamentaria
            Long codUnidade = Long.valueOf(linha.get(0).split(" - ")[0]);
            String siglaUnidade = linha.get(0).split(" - ")[1];
            UnidadeOrcamentaria unidade = unidadeOrcamentariaService.findOrCreate(codUnidade, siglaUnidade, plano);

            //cria a fontes orcamentarias
            FonteOrcamentaria fonteCaixa = fonteOrcamentariaService.findOrCreate(1l, "Caixa");
            FonteOrcamentaria fonteDemais = fonteOrcamentariaService.findOrCreate(2l, "Demais Fontes");

            //criar investimento
            Investimento investimento = new Investimento(nomePlano, unidade, plano);

            // cria o ano 2025
            Ano ano2025 = anoService.findOrCreate("2025");

            //criar execussões
            ExecucaoOrcamentaria execCaixa2025 = ExecucaoOrcamentaria.criar(ano2025, investimento,
                valorToDouble(linha.get(12)), 0, fonteCaixa);

            execCaixa2025 = execucaoOrcamentariaService.setValores(execCaixa2025);

            ExecucaoOrcamentaria execDemais2025 = ExecucaoOrcamentaria.criar(ano2025, investimento,
                valorToDouble(linha.get(21)), 0, fonteDemais);

            execDemais2025 = execucaoOrcamentariaService.setValores(execDemais2025);

            // criar objeto
            Objeto objeto = objetoService.save(new Objeto(investimento.getNome(), linha.get(4), "Projeto", investimento));

            Ano ano2024 = anoService.findOrCreate("2024");
            Ano ano2026 = anoService.findOrCreate("2026");
            Ano ano2027 = anoService.findOrCreate("2027");

            // criar custos caixa
            custoService.findOrCreate(new Custo(ano2024, objeto,
                        valorToDouble(linha.get(8)), valorToDouble(linha.get(9)), fonteCaixa));
            
            custoService.findOrCreate(new Custo(ano2025, objeto,
                        valorToDouble(linha.get(10)), valorToDouble(linha.get(11)), fonteCaixa));

            custoService.findOrCreate(new Custo(ano2026, objeto,
                        valorToDouble(linha.get(13)), valorToDouble(linha.get(14)), fonteCaixa));

            custoService.findOrCreate(new Custo(ano2027, objeto,
                        valorToDouble(linha.get(15)), valorToDouble(linha.get(16)), fonteCaixa));


            // criar custos demais fontes

            custoService.findOrCreate(new Custo(ano2024, objeto,
                        valorToDouble(linha.get(17)), valorToDouble(linha.get(18)), fonteDemais));
            
            custoService.findOrCreate(new Custo(ano2025, objeto,
                        valorToDouble(linha.get(19)), valorToDouble(linha.get(20)), fonteDemais));

            custoService.findOrCreate(new Custo(ano2026, objeto,
                        valorToDouble(linha.get(22)), valorToDouble(linha.get(23)), fonteDemais));

            custoService.findOrCreate(new Custo(ano2027, objeto,
                        valorToDouble(linha.get(24)), valorToDouble(linha.get(25)), fonteDemais));

        }       

    }

    private void salvarDadosNoBanco(){
        Logger.getGlobal().info("Efetivando a migração!!");
        for(int i = 0; i < DataMock.noCustos.size(); i++){
            Logger.getGlobal().info("Efetivando: " + (i+1) + "/" + DataMock.noCustos.size());
            custoService.saveReal(DataMock.noCustos.get(i));
        }

        // custoService.saveReal(DataMock.noCustos);
    }

    private double valorToDouble(String valor) {
        if(valor.contains("-"))
            valor = valor.split("-").length >= 2 ? valor.split("-")[1].trim() : "0";

        
        while(valor.contains("."))
            valor = valor.replace(".", "");
        
        valor = valor.replace(",", ".");
        
        return Double.parseDouble(valor);
    }

        

}
