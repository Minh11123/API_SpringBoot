package com.vti.finalexamservice.model.dto;

import com.vti.finalexamservice.model.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateAccountDTO {
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Role role;

    private long departmentId;
}
