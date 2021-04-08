package it.akademy.garden.controllers;

import it.akademy.garden.dao.FlowerDao;
import it.akademy.garden.dao.GardenDao;
import it.akademy.garden.dao.GardenerDao;
import it.akademy.garden.models.Garden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/gardens")
public class GardenController {

    private final GardenDao gardenDao;
    private final FlowerDao flowerDao;
    private final GardenerDao gardenerDao;

    @Autowired
    public GardenController(GardenDao gardenDao, FlowerDao flowerDao, GardenerDao gardenerDao){
        this.gardenDao = gardenDao;
        this.flowerDao = flowerDao;
        this.gardenerDao = gardenerDao;
    }

    @GetMapping
    public ResponseEntity<List<Garden>> getAllGardens(){
        List<Garden> gardens = gardenDao.findAll();
        return new ResponseEntity<>(gardens, HttpStatus.OK);
    }

    @GetMapping("/?name={name}")
    public ResponseEntity<List<Garden>> getAllGardensByName(@PathVariable String name){
        List<Garden> gardens = gardenDao.findAllByName(name);
        return new ResponseEntity<>(gardens, HttpStatus.OK);
    }

    @GetMapping("/?id={id}")
    public ResponseEntity<Garden> getGardenById(@PathVariable int id){
        Garden garden = gardenDao.findById(id);
        if(garden == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(garden, HttpStatus.OK);
    }
}
