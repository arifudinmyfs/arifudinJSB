package com.learnjava.arifudinJSB.services;

import com.learnjava.arifudinJSB.models.Crud;
import com.learnjava.arifudinJSB.repositorys.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CrudService {
    private final CrudRepository crudRepository;

    public CrudService(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public List<Crud> getAllCruds() {
        return crudRepository.findAll();
    }

    public Optional<Crud> getCrudById(UUID id) {
        return crudRepository.findById(id);
    }

    public Crud createCrud(Crud crud) {
        return crudRepository.save(crud);
    }

    public Crud updateCrud(UUID id, Crud crudDetails) {
        return crudRepository.findById(id).map(crud -> {
            crud.setName(crudDetails.getName());
            crud.setEmail(crudDetails.getEmail());
            return crudRepository.save(crud);
        }).orElseThrow(() -> new RuntimeException("Crud not found"));
    }

    public void deleteCrud(UUID id) {
        crudRepository.deleteById(id);
    }

    public List<Crud> searchCrudsByName(String name) {
        return crudRepository.findByNameContaining(name);
    }
}
