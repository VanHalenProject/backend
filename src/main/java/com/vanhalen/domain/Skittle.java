package com.vanhalen.domain;

import lombok.Getter;

import javax.persistence.Entity;
import java.awt.*;

@Entity
@Getter
public class Skittle {
    private Color color;

    public Skittle(Color color) {
        this.color = color;
    }
}
