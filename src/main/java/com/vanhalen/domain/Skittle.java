package com.vanhalen.domain;

import javax.persistence.Entity;
import java.awt.*;

@Entity
public class Skittle {
    private Color color;

    public Skittle(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
