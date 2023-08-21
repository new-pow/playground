package com.example.jpapractice.week4.orphan.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Table(name = "phone")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(
//            optional = true
            cascade = CascadeType.ALL
    )
    private Person owner;
    private String number;

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
