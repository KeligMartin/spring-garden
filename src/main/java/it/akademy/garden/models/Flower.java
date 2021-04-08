package it.akademy.garden.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Flower {

    @Id
    private int id;

    private String name;

    @JsonBackReference(value = "flower-gardener")
    @ManyToOne
    private Gardener gardener;

    @JsonBackReference(value = "flower-garden")
    @ManyToOne
    private Garden garden;

    public Flower(){}

    public Flower(int id, String name){
        this.id = id;
        this.name = name;
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

    public Gardener getGardener() {
        return gardener;
    }

    public void setGardener(Gardener gardener) {
        this.gardener = gardener;
    }
}
