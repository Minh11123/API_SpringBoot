package com.vti.finalexamservice.controller;

import com.vti.finalexamservice.contain.ServiceContext;
import com.vti.finalexamservice.model.entity.Department;
import com.vti.finalexamservice.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/department")
public class DepartmentController {
    @Autowired
    IDepartmentService service;

    @GetMapping("/get-all")
    ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }

    @PostMapping("/search")
    ResponseEntity search(@RequestBody ServiceContext serviceContext) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.search(serviceContext));
    }

    @GetMapping("/{id}")
    ResponseEntity getById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getByID(id));
    }

    @PostMapping("/create")
    ResponseEntity create(@RequestBody Department requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(requestDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.delete(id));
    }

    @PutMapping("/update")
    ResponseEntity update(@RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(department));
    }

    @DeleteMapping("/delete-by-ids")
    public ResponseEntity deleteByIds(@RequestBody long[] ids) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.deleteByIds(ids));
    }
}
