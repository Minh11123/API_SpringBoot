package com.vti.finalexamservice.service;

import com.vti.finalexamservice.contain.ServiceContext;
import com.vti.finalexamservice.model.entity.Department;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAll();

    Page<Department> search(ServiceContext serviceContext);

    Department getByID(long id);

    Department create(Department department);

    boolean delete(long id);

    boolean deleteByIds(long[] id);

    Department update(Department department);
}
