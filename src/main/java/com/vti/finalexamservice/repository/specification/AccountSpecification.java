package com.vti.finalexamservice.repository.specification;

import com.vti.finalexamservice.contain.ServiceContext;
import com.vti.finalexamservice.contain.ServiceParam;
import com.vti.finalexamservice.model.entity.Account;
import com.vti.finalexamservice.model.entity.Department;
import com.vti.finalexamservice.model.entity.Role;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Join;

@Data
public class AccountSpecification {
    public static Specification<Account> buildCondition(ServiceContext context) {
        return Specification.where(getUsername(context))
                .and(getRole(context))
                .or(getFirstName(context))
                .and(getDepartmentName(context));
    }

    public static Specification<Account> getUsername(ServiceContext context) {
        if (!CollectionUtils.isEmpty(context.getParams())) {
            for (ServiceParam param : context.getParams()) {
                if (param.getProperty().equals("username")) {
                    return (root, query, cri) -> {
                        return cri.like(cri.lower(root.get("username")), "%" + param.getValue() + "%");
                    };
                }
            }
        }
        return null;
    }

    public static Specification<Account> getRole(ServiceContext context) {
        for (ServiceParam param : context.getParams()) {
            if (param.getProperty().equals("role") && StringUtils.isNotBlank(param.getValue())) {
                return (root, query, cri) -> {
                    return cri.equal(root.get("role"), Role.valueOf(param.getValue()));
                };
            }
        }
        return null;
    }

    public static Specification<Account> getFirstName(ServiceContext context) {
        for (ServiceParam param : context.getParams()) {
            if (param.getProperty().equals("firstName") && StringUtils.isNotBlank(param.getValue())) {
                return (root, query, cri) -> {
                    return cri.like(cri.lower(root.get("firstName")), "%" + param.getValue() + "%");
                };
            }
        }
        return null;
    }

    public static Specification<Account> getDepartmentName(ServiceContext context) {
        for (ServiceParam param : context.getParams()) {
            if (param.getProperty().equals("departmentName") && StringUtils.isNotBlank(param.getValue())) {
                return (root, query, cri) -> {
                    Join<Account, Department> join = root.join("department");
                    return cri.like(cri.lower(join.get("name")), "%" + param.getValue().toLowerCase() + "%");
                };
            }
        }
        return null;
    }
}
