package it.akademy.garden.dao;

import it.akademy.garden.models.Gardener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GardenerDao extends JpaRepository<Gardener, Integer> {
    List<Gardener> findAll();
    Gardener findById(int id);
    Gardener save(Gardener gardener);
    void deleteById(int id);
}
