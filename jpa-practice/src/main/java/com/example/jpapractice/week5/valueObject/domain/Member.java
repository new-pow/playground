package com.example.jpapractice.week5.valueObject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Target;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Embedded
    private Coordinates connectedLocation;

    @ElementCollection
    @Target(ConnectedLocation.class)
    @CollectionTable(name = "location_history"
            , joinColumns = @JoinColumn(name= "member_id", referencedColumnName = "id")
//            ,joinColumns = @JoinColumn(name = "id")
//            ,foreignKey = @ForeignKey(name = "member_id")
    )
    private List<Coordinates> connectedLocations = new ArrayList<>();

    @Builder
    public Member(Long id, String name, Coordinates connectedLocation, List<Coordinates> connectedHistory) {
        this.id = id;
        this.name = name;
        this.connectedLocation = connectedLocation;
        this.connectedLocations = connectedHistory;
    }

    public void addLocationHistory(Coordinates coordinates) {
        this.connectedLocation = coordinates;
        this.connectedLocations.add(coordinates);
    }
}
