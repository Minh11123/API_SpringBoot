package com.vti.finalexamservice.service.impl;

import com.vti.finalexamservice.config.exceptions.AppException;
import com.vti.finalexamservice.config.exceptions.ErrorResponseBase;
import com.vti.finalexamservice.contain.CheckManager;
import com.vti.finalexamservice.contain.ServiceContext;
import com.vti.finalexamservice.contain.ServiceParam;
import com.vti.finalexamservice.model.entity.Department;
import com.vti.finalexamservice.repository.DepartmentRepository;
import com.vti.finalexamservice.service.IDepartmentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentService implements IDepartmentService {

    @Autowired
    DepartmentRepository repository;

    @Override
    public List<Department> getAll() {
        return repository.findAll();
    }

    @Override
    @SneakyThrows
    public Page<Department> search(ServiceContext serviceContext) {
        try {
            CheckManager.checkServiceContext(serviceContext);
            PageRequest pageRequest = CheckManager.checkPageable(serviceContext);
            CheckManager.checkServiceContext(serviceContext);

            List<ServiceParam> serviceParams = serviceContext.getParams();
            Optional<ServiceParam> serviceParam = serviceParams.stream().filter
                    (e -> e.getProperty().equals("textual")).findFirst();
            String name = serviceParam.get().getValue();
            return repository.findByNameContains(name, pageRequest);
        } catch (Exception ex) {
            throw new AppException(ex);
        }
    }

    @Override
    public Department getByID(long id) {
        if (repository.findById(id).isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED);
        }
        try {
            return repository.getById(id);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    @Override
    public Department create(Department department) {
        if (repository.findById(department.getId()).isPresent()) {
            throw new AppException(ErrorResponseBase.IS_EXISTED);
        }
        try {
            return repository.save(department);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    @Override
    @SneakyThrows
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
    @SneakyThrows
    public Department update(Department department) {
        if (repository.findById(department.getId()).isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED);
        }
        try {
            return repository.save(department);
        } catch (Exception e) {
            throw new AppException(e);
        }

    }
}
