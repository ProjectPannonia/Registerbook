package com.registerbook.registerbook.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "MUSICINSTRUMENT")
public class MusicInstrument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "INSTRUMENTNAME")
    private String instrumentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }
}
