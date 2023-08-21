package com.example.jpapractice.week4.orphan.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "person")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne(
//            cascade = CascadeType.ALL,
            orphanRemoval = true
//            mappedBy = "person"
    )
    private Address address;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
//            , mappedBy = "owner"
    )
    private final List<Phone> ownPhone = new ArrayList<>();

    public void setPhone(Phone phone) {
        ownPhone.add(phone);
    }

    public void updateAddress(Address address2) {
        this.address = address2;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", ownPhone=" + ownPhone +
                '}';
    }

    public void resetAddress() {
        this.address = null;
    }
}
