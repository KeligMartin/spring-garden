package it.akademy.garden.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonManagedReference
    @OneToMany
    private List<Gardener> gardeners;

    @JsonManagedReference
    @OneToMany
    private List<Flower> flowers;

    public Garden(){}

    public Garden(String name){
        this.name = name;
        this.gardeners = new ArrayList<>();
        this.flowers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Gardener> getGardeners() {
        return gardeners;
    }

    public void setGardeners(List<Gardener> gardeners) {
        this.gardeners = gardeners;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }
}
