package com.example.jpapractice.week4.orphan.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "team")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    @OneToMany(mappedBy = "team", orphanRemoval = false)
//    private List<Person> mates = new ArrayList<>();
}
