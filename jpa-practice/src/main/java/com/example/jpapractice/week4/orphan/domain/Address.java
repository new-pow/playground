package com.example.jpapractice.week4.orphan.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Table(name = "address")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @OneToOne
//    private Person person;
    private String value;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
