package com.example.jpapractice.week4.orphan.service;

import com.example.jpapractice.week4.orphan.domain.Address;
import com.example.jpapractice.week4.orphan.domain.Person;
import com.example.jpapractice.week4.orphan.domain.Phone;
import com.example.jpapractice.week4.orphan.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrphanServiceTest {
    @Autowired
    EntityManager em;
    Address address;
    Person person;
    Phone phone;
    Logger log = LoggerFactory.getLogger(OrphanServiceTest.class);

    @BeforeEach
    void init() {
        address = Address.builder()
                .value("서울시 강남구").build();
        phone = Phone.builder()
                .number("010-0000-0000")
                .build();
        person = Person.builder()
                .name("새힘")
                .address(address)
                .build();

        person.setPhone(phone);

        em.persist(phone);
        em.persist(address);
        em.persist(person);
        em.flush();
        System.out.println("----- BEFORE EACE -----");
    }
    
    @Test
    public void saveTest() throws Exception{
        em.persist(address);
        em.persist(phone);
        em.persist(person);
        em.flush();
    }

    @Test
    @DisplayName("일대일 관계일 때 다른 자식 객체로 수정합니다.")
    public void oneToOne_Test_update() throws Exception{
        Address address2 = Address.builder()
                .value("서울시 성북구")
                .build();
        em.persist(address2);

        person.updateAddress(address2);
        em.flush();
    }

//    @Test
//    @DisplayName("일대다 관계일 때 다른 자식 객체를 수정합니다.")
//    public void oneToMany_Test_update() throws Exception{
//        Phone phone2 = Phone.builder()
//                .number("000-0000-1111")
//                .build();
//        em.persist(phone2);
//
//        em.flush();
//    }

    @Test
    @DisplayName("일대일 관계일 때 관계를 삭제합니다.")
    public void oneToOne_Test_relation_remove() throws Exception{
        System.out.println(person.toString());
        person.resetAddress();
        em.flush();

        System.out.println(person.toString());
    }

    @Test
    @DisplayName("부모 객체를 삭제합니다.")
    public void all_Test_parent_delete() throws Exception{
        em.remove(person);
        em.flush();
    }

    @Test
    @DisplayName("일대다 관계일 때 관계를 삭제합니다.")
    public void oneToMany_Test_relating_remove() throws Exception{
        System.out.println(person.toString());
        person.getOwnPhone().remove(0);
        em.flush();
        System.out.println(person.toString());
    }

}
