package com.vti.finalexamservice.repository;

import com.vti.finalexamservice.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    Optional<Account> findByUsername(String username);

    @Modifying
    @Query(value = "SELECT permission_id FROM roles_permission WHERE role_id = :role_id", nativeQuery = true)
    List<String> findAllPermissionByRole(@Param("role_id") Long role_id);

    @Modifying
    @Query(value = "SELECT name FROM ROLES_PERMISSION WHERE role_id = (:roleId)", nativeQuery = true)
    Collection<String> listPermissionByRole(@Param("roleId") Long roleId);
}
