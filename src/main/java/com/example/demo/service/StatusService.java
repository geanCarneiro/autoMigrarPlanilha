package com.example.demo.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmStatus;
import com.example.demo.model.Objeto;
import com.example.demo.model.Status;
import com.example.demo.model.StatusEnum;
import com.example.demo.repository.StatusRepository;


@Service
public class StatusService {
    
    @Autowired
    private StatusRepository repository;

    private ObjetoService objetoService;

    public Status findOrCreate(Status status) {
        
        Status statusProbe = new Status();
        statusProbe.setNome(status.getNome());

        Optional<Status> optStatus = repository.findBy(Example.of(statusProbe), q -> q.first());

        return optStatus.orElse(status);

    }

    public List<Status> findAllStatusObjeto(){
        return repository.findAllStatusObjeto();
    }

    public List<Status> findAll(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Optional<Status> getByStatusId(String statusId){

        Status statusProbe = new Status();
        statusProbe.setStatusId(StatusEnum.valueOf(statusId));

        return repository.findBy(Example.of(statusProbe), q -> q.first());

    }

    public Status findById(String statusId) {
        return repository.findById(statusId).orElse(null);
    }

    public void aplicarStatus(Objeto objeto, Status status) {
        
        EmStatus emStatus = new EmStatus();
        emStatus.setStatus(status);
        emStatus.setTimestamp(ZonedDateTime.now());

        objeto.setEmStatus(emStatus);

        objetoService.save(objeto);
    }

    @Autowired
    public void setObjetoService(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }
}
