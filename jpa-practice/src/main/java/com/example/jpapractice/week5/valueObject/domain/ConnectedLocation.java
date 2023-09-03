package com.example.jpapractice.week5.valueObject.domain;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class ConnectedLocation implements Coordinates, Cloneable{

    private double latitude; // 위도
    private double longitude; // 경도

    public ConnectedLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public double x() {
        return latitude;
    }

    @Override
    public double y() {
        return longitude;
    }

    @Override
    public void changeX(double newX) {
        this.latitude = newX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectedLocation that = (ConnectedLocation) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public ConnectedLocation clone() {
        try {
            ConnectedLocation clone = (ConnectedLocation) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
