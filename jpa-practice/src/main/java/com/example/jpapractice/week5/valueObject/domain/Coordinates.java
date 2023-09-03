package com.example.jpapractice.week5.valueObject.domain;

import java.io.Serializable;

public interface Coordinates extends Cloneable, Serializable { // 좌표
    double x();
    double y();
    void changeX(double newX);
}
