package com.vti.finalexamservice.service;


import com.vti.finalexamservice.contain.ServiceContext;
import com.vti.finalexamservice.model.dto.AccountRequest;
import com.vti.finalexamservice.model.dto.CreateAccountDTO;
import com.vti.finalexamservice.model.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {

    List<Account> getAllUsers(AccountRequest request);

    Page<Account> search(ServiceContext serviceContext);

    Account getByID(long id);

    Account create(CreateAccountDTO request);

    boolean delete(long id);

    boolean deleteByIds(long[] id);

    Account update(CreateAccountDTO request);
}
