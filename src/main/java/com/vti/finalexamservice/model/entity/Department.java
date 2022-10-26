package com.vti.finalexamservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_member")
    private int totalMember;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeDepartment type;

    @Column(name = "created_date")
    private LocalDate createdDate;
}
