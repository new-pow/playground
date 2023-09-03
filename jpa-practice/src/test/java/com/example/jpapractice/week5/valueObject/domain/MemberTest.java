package com.example.jpapractice.week5.valueObject.domain;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    EntityManager em;
    Member member;
    ConnectedLocation location;

    @BeforeEach
    void init () {
        location = new ConnectedLocation(100, 200);
        member = Member.builder()
                .name("새힘")
                .connectedLocation(location)
                .connectedHistory(List.of(location))
                .build();
        em.persist(member);
        em.flush();
        System.out.println("----- BEFORE EACE -----");
    }

    @Test
    void saveTest() throws Exception{
        Long id = member.getId();
        Member member1 = em.find(Member.class, id);
        assertThat(member.equals(member1)).isTrue();
    }

    @Test
    void sharedLocation() throws Exception{
        Long id = member.getId();
        Member member2 = Member.builder()
                .name("브루니")
                .connectedLocation(location)
                .build();
        em.persist(member2);

        Coordinates connectedLocation = member2.getConnectedLocation();
        connectedLocation.changeX(500);

        assertThat(member.getConnectedLocation().x()).isEqualTo(500);
    }

    @Test
    void notSharedLocation_newLocation() throws Exception{
        Long id = member.getId();
        Member member2 = Member.builder()
                .name("루미두")
                .connectedLocation(new ConnectedLocation(location.x(), location.y()))
                .build();
        em.persist(member2);

        Coordinates connectedLocation = member2.getConnectedLocation();
        connectedLocation.changeX(500);

        assertThat(member.getConnectedLocation().x()).isNotEqualTo(500);
    }

    @Test
    void notSharedLocation_clone() throws Exception{
        Long id = member.getId();
        Member member2 = Member.builder()
                .name("루미두")
                .connectedLocation(location.clone())
                .build();
        em.persist(member2);

        Coordinates connectedLocation = member2.getConnectedLocation();
        connectedLocation.changeX(500);

        assertThat(member.getConnectedLocation().x()).isNotEqualTo(500);
    }

    @Test
    void elementalCollectionTest() throws Exception{
        Coordinates coordinates = new ConnectedLocation(500, 500);
        member.addLocationHistory(coordinates);

        assertThat(member.getConnectedLocations()).hasSize(2);
    }
}
