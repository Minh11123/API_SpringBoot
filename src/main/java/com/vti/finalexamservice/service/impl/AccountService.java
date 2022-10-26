package com.vti.finalexamservice.service.impl;


import com.vti.finalexamservice.config.exceptions.AppException;
import com.vti.finalexamservice.config.exceptions.ErrorResponseBase;
import com.vti.finalexamservice.contain.CheckManager;
import com.vti.finalexamservice.contain.ServiceContext;
import com.vti.finalexamservice.model.dto.AccountRequest;
import com.vti.finalexamservice.model.dto.CreateAccountDTO;
import com.vti.finalexamservice.model.entity.Account;
import com.vti.finalexamservice.model.entity.Department;
import com.vti.finalexamservice.repository.AccountRepository;
import com.vti.finalexamservice.repository.DepartmentRepository;
import com.vti.finalexamservice.repository.specification.AccountSpecification;
import com.vti.finalexamservice.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Account> getAllUsers(AccountRequest request) {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            throw new AppException(ex);
        }
    }

    @Override
    public Page<Account> search(ServiceContext serviceContext) {
        try {
            CheckManager.checkServiceContext(serviceContext);
            PageRequest pageRequest = CheckManager.checkPageable(serviceContext);

            Specification<Account> condition = AccountSpecification.buildCondition(serviceContext);

            return repository.findAll(condition, pageRequest);
        } catch (Exception ex) {
            throw new AppException(ex);
        }
    }


    @Override
    public Account getByID(long id) {
        Optional<Account> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED_ACCOUNT);
        }
        try {
            return optional.get();
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    @Override
    public Account create(CreateAccountDTO request) {
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new AppException(ErrorResponseBase.IS_EXISTED);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(request.getDepartmentId());
        if (optionalDepartment.isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED_DEPARTMENT);
        }
        try {
            Account entity = new Account();
            BeanUtils.copyProperties(request, entity);
            entity.setDepartment(optionalDepartment.get());
            return repository.save(entity);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        if (repository.findById(id).isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED);
        }
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    @Override
    public boolean deleteByIds(long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            delete(ids[i]);
        }
        return true;
    }

    @Override
    public Account update(CreateAccountDTO request) {
        Optional<Account> optionalAccount = repository.findById(request.getId());
        if (optionalAccount.isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED_ACCOUNT);
        }

        Account entity = optionalAccount.get();
        Optional<Department> optionalDepartment = departmentRepository.findById(request.getDepartmentId());
        if (optionalDepartment.isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED_DEPARTMENT);
        }
        try {
            entity.setDepartment(optionalDepartment.get());
            entity.setFirstName(request.getFirstName());
            entity.setLastName(request.getLastName());
            entity.setRole(request.getRole());
            return repository.save(entity);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

}
