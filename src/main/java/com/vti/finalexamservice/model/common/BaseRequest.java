package com.vti.finalexamservice.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest {
    private int page;
    private int size;
    private String sortType;
    private String sortField;
}
