package it.akademy.garden.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gardener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lastName;

    private String firstName;

    @JsonManagedReference(value = "flower-gardener")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Flower> flowers;

    @JsonBackReference(value = "garden-gardeners")
    @ManyToOne
    private Garden garden;

    public Gardener(){}

    public Gardener(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.flowers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }
}
