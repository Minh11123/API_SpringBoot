package com.vti.finalexamservice.controller;

import com.vti.finalexamservice.contain.ServiceContext;
import com.vti.finalexamservice.model.dto.AccountRequest;
import com.vti.finalexamservice.model.dto.CreateAccountDTO;
import com.vti.finalexamservice.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    private final IAccountService service;

    @Autowired
    public AccountController(IAccountService service) {
        this.service = service;
    }

    @GetMapping(value = "/search")
    public ResponseEntity getAllUsers(AccountRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllUsers(request));
    }

    @PostMapping("/search")
    ResponseEntity search(@RequestBody ServiceContext serviceContext) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.search(serviceContext));
    }

    @GetMapping("/{id}")
//	@PreAuthorize("ADMIN")
    ResponseEntity getById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getByID(id));
    }

    @PostMapping("/create")
    ResponseEntity create(@RequestBody CreateAccountDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.delete(id));
    }

    @PostMapping("/update")
    ResponseEntity update(@RequestBody CreateAccountDTO account) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(account));
    }

    @DeleteMapping("/delete-by-ids")
    public ResponseEntity deleteByIds(@RequestBody long[] ids) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.deleteByIds(ids));
    }
}
