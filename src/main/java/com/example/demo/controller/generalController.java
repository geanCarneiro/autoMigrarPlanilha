package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AreaTematica;
import com.example.demo.model.Conta;
import com.example.demo.model.Custo;
import com.example.demo.model.EmStatus;
import com.example.demo.model.Entidade;
import com.example.demo.model.ExecucaoOrcamentaria;
import com.example.demo.model.FonteOrcamentaria;
import com.example.demo.model.IndicadaPor;
import com.example.demo.model.Investimento;
import com.example.demo.model.Localidade;
import com.example.demo.model.Objeto;
import com.example.demo.model.PlanoOrcamentario;
import com.example.demo.model.Status;
import com.example.demo.model.StatusEnum;
import com.example.demo.model.TipoPlano;
import com.example.demo.model.UnidadeOrcamentaria;
import com.example.demo.model.VinculadaPor;
import com.example.demo.service.AnoService;
import com.example.demo.service.AreaTematicaService;
import com.example.demo.service.CustoService;
import com.example.demo.service.ExecucaoOrcamentariaService;
import com.example.demo.service.FonteOrcamentariaService;
import com.example.demo.service.InvestimentoService;
import com.example.demo.service.LocalidadeService;
import com.example.demo.service.ObjetoService;
import com.example.demo.service.PlanoOrcamentarioService;
import com.example.demo.service.StatusService;
import com.example.demo.service.TipoPlanoService;
import com.example.demo.service.UnidadeOrcamentariaService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("teste")
@RequiredArgsConstructor
public class generalController {

    private final PlanoOrcamentarioService planoOrcamentarioService;
    private final UnidadeOrcamentariaService unidadeOrcamentariaService;
    private final FonteOrcamentariaService fonteOrcamentariaService;
    private final ObjetoService objetoService;
    private final InvestimentoService investimentoService;
    private final StatusService statusService;
    private final AreaTematicaService areaTematicaService;
    private final TipoPlanoService tipoPlanoService;
    private final LocalidadeService localidadeService;
    
    @GetMapping("")
    public ResponseEntity<String> teste() {

        try{
            File excel = ResourceUtils.getFile("classpath:planilha/Planilha PIP 25 - Para a CGTI (6).xlsx");

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
            if(linha.get(0).isBlank()) continue;
            Logger.getGlobal().info("Consumindo: Plano - " + linha.get(2) + "; Unidade - " + linha.get(0).split(" - ")[1]);

            FonteOrcamentaria fonteCaixa = fonteOrcamentariaService.findByCodigo("000000001").get();
            FonteOrcamentaria fonteDemais = fonteOrcamentariaService.findByCodigo("000000002").get();

            double[] messesZerado = new double[12];
            Arrays.fill(messesZerado, 0);

            Status statusCadastrado = statusService.getByStatusId(StatusEnum.CADASTRADO.name()).get();

            EmStatus emCadastrado = new EmStatus(
                statusCadastrado, 
                ZonedDateTime.now()
            );

            String _unidadeReponsavel = linha.get(0);
            String _emailResponsavel = linha.get(1);
            String _codPo = linha.get(2);
            String _nomePo = linha.get(3);
            String _descObjeto = linha.get(4);
            String _microrregião = linha.get(5);
            String _areaTematica = linha.get(6);
            String _contrato = linha.get(7);
            String _previstoCaixa2024 = linha.get(8);
            String _contratadoCaixa2024 = linha.get(9);
            String _previstoCaixa2025 = linha.get(10);
            String _contratadoCaixa2025 = linha.get(11);
            String _orcadoCaixa2025 = linha.get(12);
            String _previstoCaixa2026 = linha.get(13);
            String _contratadoCaixa2026 = linha.get(14);
            String _previstoCaixa2027 = linha.get(15);
            String _contratadoCaixa2027	= linha.get(16);
            String _previstoDemaisFontes2024 = linha.get(17);
            String _contratadoDemaisFontes2024 = linha.get(18);
            String _previstoDemaisFontes2025 = linha.get(19);
            String _contratadoDemaisFontes2025 = linha.get(20);
            String _orcadoDemaisFontes2025 = linha.get(21);
            String _previstoDemaisFontes2026 = linha.get(22);
            String _contratadoDemaisFontes2026 = linha.get(23);
            String _previstoDemaisFontes2027 = linha.get(24);
            String _contratadoDemaisFontes2027 = linha.get(25);

            
            // preparar plano orcamentario
            PlanoOrcamentario plano = planoOrcamentarioService.findOrCreateByCod(
                new PlanoOrcamentario(String.format("%6s", _codPo).replace(' ', '0'), _nomePo, null)
            );

            // prepara unidade Orçamentaria
            String codUnidade = _unidadeReponsavel.split(" - ")[0];
            String siglaUnidade = _unidadeReponsavel.split(" - ")[1];
            UnidadeOrcamentaria unidade = unidadeOrcamentariaService.findOrCreateByCod(
                new UnidadeOrcamentaria(codUnidade, null, siglaUnidade, null)
            );

            //criar investimento
            Investimento investimento = investimentoService.findOrCreateByUoPo(
                new Investimento(
                    plano, 
                    unidade, 
                    Arrays.asList(
                        new ExecucaoOrcamentaria(
                            2025, 
                            new HashSet<>(Arrays.asList(
                                new VinculadaPor(
                                    fonteCaixa, 
                                    0, 
                                    0, 
                                    messesZerado, 
                                    messesZerado, 
                                    messesZerado, 
                                    this.valorToDouble(_orcadoCaixa2025) 
                                    ),
                                new VinculadaPor(
                                    fonteDemais, 
                                    0, 
                                    0, 
                                    messesZerado, 
                                    messesZerado, 
                                    messesZerado, 
                                    this.valorToDouble(_orcadoDemaisFontes2025))
                            ))

                        )
                    ))
            );

            // pega ou cria area
            String areaNome = _areaTematica.split("\\. ", 2)[1];

            AreaTematica area = areaTematicaService.findOrCreateByNome(
                new AreaTematica(areaNome)
            );

            // pega ou cria microrregiao
            String microrregiaoNome = _microrregião.isBlank() ? null : _microrregião.split(" - ")[1];

            Localidade microrregiao = microrregiaoNome == null ? null : localidadeService.findOrCreateByNome(
                new Localidade(microrregiaoNome)
            );

            // pega plano tipo PIP
            TipoPlano tipoPlanoPIP = tipoPlanoService.findBySigla("PIP").get();

            // criar objeto
            Objeto objeto = new Objeto(
                _nomePo, 
                _descObjeto, 
                "Projeto", 
                0, 
                "", 
                _contrato, 
                emCadastrado, 
                null, 
                area, 
                Arrays.asList(tipoPlanoPIP), 
                null, 
                new ArrayList<Custo>(Arrays.asList(
                    new Custo(2024, 
                        new HashSet<IndicadaPor>(Arrays.asList(
                            new IndicadaPor(fonteCaixa, this.valorToDouble(_previstoCaixa2024), this.valorToDouble(_contratadoCaixa2024)),
                            new IndicadaPor(fonteDemais, this.valorToDouble(_previstoDemaisFontes2024), this.valorToDouble(_contratadoDemaisFontes2024))
                        ))
                    ),
                    new Custo(2025, 
                        new HashSet<IndicadaPor>(Arrays.asList(
                            new IndicadaPor(fonteCaixa, this.valorToDouble(_previstoCaixa2025), this.valorToDouble(_contratadoCaixa2025)),
                            new IndicadaPor(fonteDemais, this.valorToDouble(_previstoDemaisFontes2025), this.valorToDouble(_contratadoDemaisFontes2025))
                        ))
                    ),
                    new Custo(2026, 
                        new HashSet<IndicadaPor>(Arrays.asList(
                            new IndicadaPor(fonteCaixa, this.valorToDouble(_previstoCaixa2026), this.valorToDouble(_contratadoCaixa2026)),
                            new IndicadaPor(fonteDemais, this.valorToDouble(_previstoDemaisFontes2026), this.valorToDouble(_contratadoDemaisFontes2026))
                        ))
                    ),
                    new Custo(2027, 
                        new HashSet<IndicadaPor>(Arrays.asList(
                            new IndicadaPor(fonteCaixa, this.valorToDouble(_previstoCaixa2027), this.valorToDouble(_contratadoCaixa2027)),
                            new IndicadaPor(fonteDemais, this.valorToDouble(_previstoDemaisFontes2027), this.valorToDouble(_contratadoDemaisFontes2027))
                        ))
                    )
                )), 
                microrregiao, 
                investimento, 
                null, 
                null
            );

            objetoService.save(objeto);

        }       

    }

    private void salvarDadosNoBanco(){
        Logger.getGlobal().info("Efetivando a migração!!");
        // for(int i = 0; i < DataMock.noCustos.size(); i++){
        //     Logger.getGlobal().info("Efetivando: " + (i+1) + "/" + DataMock.noCustos.size());
        //     custoService.saveReal(DataMock.noCustos.get(i));
        // }

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
