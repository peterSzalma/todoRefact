package com.codecool.todorefact.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Todo {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private boolean status;

}
