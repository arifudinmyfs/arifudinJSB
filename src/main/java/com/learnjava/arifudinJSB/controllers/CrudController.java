package com.learnjava.arifudinJSB.controllers;

import com.learnjava.arifudinJSB.models.Crud;
import com.learnjava.arifudinJSB.services.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cruds")
public class CrudController {
    private final CrudService crudService;

    public CrudController(CrudService crudService) {
        this.crudService = crudService;
    }

    @GetMapping
    public List<Crud> getAllCruds() {
        return crudService.getAllCruds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crud> getCrudById(@PathVariable UUID id) {
        return crudService.getCrudById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Crud createCrud(@RequestBody Crud user) {
        return crudService.createCrud(user);
    }

    @PutMapping("/{id}")
    public Crud updateCrud(@PathVariable UUID id, @RequestBody Crud user) {
        return crudService.updateCrud(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrud(@PathVariable UUID id) {
        crudService.deleteCrud(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Crud> searchCrudsByName(@RequestParam String name) {
        return crudService.searchCrudsByName(name);
    }
}