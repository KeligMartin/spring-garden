package it.akademy.garden.dao;

import it.akademy.garden.models.Gardener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GardenerDao extends JpaRepository<Gardener, Integer> {
    List<Gardener> findAll();

    Gardener findById(int id);

    Gardener save(Gardener gardener);

    void deleteById(int id);

}
