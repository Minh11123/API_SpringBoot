package com.vti.finalexamservice.contain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class CheckManager {

    public static ServiceContext checkServiceContext(ServiceContext serviceContext) {
        try {
            if (serviceContext.getPageSize() == null || serviceContext.getPageSize() == 0) {
                serviceContext.setPageSize(10);
            }
            if (serviceContext.getPageNumber() == null || serviceContext.getPageNumber() == 0) {
                serviceContext.setPageNumber(0);
            } else {
                serviceContext.setPageNumber(serviceContext.getPageNumber() - 1);
            }
            if (serviceContext.getSortType() == null || serviceContext.getSortType().equals("") || serviceContext.getSortType().equals("DESC")) {
                serviceContext.setSortType("DESC");
            } else {
                serviceContext.setSortType("ASC");
            }
            if (serviceContext.getSortField() == null || serviceContext.getSortField().equals("")) {
                serviceContext.setSortField("id");
            }
        } catch (Exception e) {
        }
        return serviceContext;
    }

    public static PageRequest checkPageable(ServiceContext serviceContext) {
        PageRequest pageRequest = null;
        if (serviceContext.getSortType().equals("DESC")) {
            pageRequest = PageRequest.of(serviceContext.getPageNumber(), serviceContext.getPageSize(), Sort.by(serviceContext.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(serviceContext.getPageNumber(), serviceContext.getPageSize(), Sort.by(serviceContext.getSortField()).ascending());
        }
        return pageRequest;
    }
}
