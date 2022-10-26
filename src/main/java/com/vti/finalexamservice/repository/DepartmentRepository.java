package com.vti.finalexamservice.repository;

import com.vti.finalexamservice.model.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    //    @Modifying
//    @Query(value = "SELECT t.* FROM TestingSystem.Department t WHERE name LIKE '%%'", nativeQuery = true)
    Page<Department> findByNameContains(String name, Pageable pageRequest);
}
