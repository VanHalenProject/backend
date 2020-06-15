package com.vanhalen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.awt.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "skittles")
public class Skittle {
    @Id
    @JsonIgnore
    private UUID id;

    private int red;

    private int orange;

    private int yellow;

    private int green;

    private int purple;

    public Skittle() {
    }

    public Skittle(int red, int orange, int yellow, int green, int purple) {
        this.red = red;
        this.orange = orange;
        this.yellow = yellow;
        this.green = green;
        this.purple = purple;
    }

    @Override
    public String toString() {
        return new StringBuilder(red+";"+orange+";"+yellow+";"+green+";"+purple+";").toString();
    }
}
