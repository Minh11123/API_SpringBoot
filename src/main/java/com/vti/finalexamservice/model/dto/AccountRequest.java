package com.vti.finalexamservice.model.dto;

import com.vti.finalexamservice.model.common.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AccountRequest extends BaseRequest {
    private String search;
    private String role;
}
