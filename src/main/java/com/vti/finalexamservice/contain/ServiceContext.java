package com.vti.finalexamservice.contain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceContext {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortField;
    private String sortType;
    protected List<ServiceParam> params;
}
