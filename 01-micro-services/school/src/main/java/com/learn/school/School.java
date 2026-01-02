package com.learn.school;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class School {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String email;
}
