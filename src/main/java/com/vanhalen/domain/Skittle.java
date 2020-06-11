package com.vanhalen.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.*;
import java.util.UUID;

@Entity
@Getter
public class Skittle {
    @Id
    private UUID id;

    private int red;

    private int orange;

    private int yellow;

    private int green;

    private int purple;

    public Skittle() {
    }

    @Override
    public String toString() {
        return new StringBuilder(red+";"+orange+";"+yellow+";"+green+";"+purple+";").toString();
    }
}
