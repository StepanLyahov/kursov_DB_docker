package com.stepan.kursov.Model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@NamedQuery(name = "CPU.getAll", query = "SELECT c from CPU c")
public class CPU {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "manufacturer")
    private String manufacturer; // производитель

    @Column(name = "series")
    private String series; // серия

    @Column(name = "core")
    private String core;

    @Column(name = "socket")
    private String socket;

    @Column(name = "count_core")
    private Integer count_core;

    @Column(name = "frequency")
    private String frequency; // частота процессора

    @Column(name = "inserted_GPU")
    private Boolean inserted_GPU;

    public CPU() { }

    public CPU(String manufacturer, String series, String core, String socket, Integer count_core, String frequency, Boolean inserted_GPU) {
        this.manufacturer = manufacturer;
        this.series = series;
        this.core = core;
        this.socket = socket;
        this.count_core = count_core;
        this.frequency = frequency;
        this.inserted_GPU = inserted_GPU;
    }
}
