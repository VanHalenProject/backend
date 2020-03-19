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

    private Color color;

    public Skittle(Color color) {
        this.color = color;
    }

    public Skittle() {

    }
}
