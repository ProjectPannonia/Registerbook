package com.registerbook.registerbook.model;

import java.awt.image.BufferedImage;

public class BandNameAndLogo {
    String name;
    BufferedImage bandLogo;

    public BandNameAndLogo(String name, BufferedImage bandLogo) {
        this.name = name;
        this.bandLogo = bandLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getBandLogo() {
        return bandLogo;
    }

    public void setBandLogo(BufferedImage bandLogo) {
        this.bandLogo = bandLogo;
    }
}
