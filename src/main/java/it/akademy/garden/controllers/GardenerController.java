package it.akademy.garden.controllers;

import it.akademy.garden.dao.FlowerDao;
import it.akademy.garden.dao.GardenerDao;
import it.akademy.garden.models.Flower;
import it.akademy.garden.models.Gardener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gardeners")
public class GardenerController {

    private final GardenerDao gardenerDao;
    private final FlowerDao flowerDao;

    @Autowired
    public GardenerController(GardenerDao gardenerDao, FlowerDao flowerDao){
        this.gardenerDao = gardenerDao;
        this.flowerDao = flowerDao;
    }

    @GetMapping
    public ResponseEntity<List<Gardener>> getAllGardeners(@RequestParam(required = false) String name){
        List<Gardener> gardeners;
        if(name == null){
            gardeners = gardenerDao.findAll();
            return new ResponseEntity<>(gardeners, HttpStatus.OK);
        }
        gardeners = gardenerDao.findAllByName(name);
        return new ResponseEntity<>(gardeners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gardener> getGardenerById(@PathVariable int id){
        Gardener gardener = gardenerDao.findById(id);

        if(gardener == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gardener, HttpStatus.OK);
    }

    @GetMapping("/{id}/flowers")
    public ResponseEntity<List<Flower>> getFlowersByGardener(@PathVariable int id){
        Gardener gardener = gardenerDao.findById(id);

        if(gardener == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Flower> flowers = flowerDao.findAllByGardener(gardener);

        return new ResponseEntity<>(flowers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Gardener> createGardener(@RequestBody Gardener gardener){
        Gardener addedGardener = gardenerDao.save(gardener);
        return new ResponseEntity<>(addedGardener, HttpStatus.CREATED);
    }

    @PutMapping("/{gardenerId}/flowers/{flowerId}")
    public ResponseEntity<Gardener> addFlowerInGardener(@PathVariable int gardenerId, @PathVariable int flowerId){
        Gardener gardener = gardenerDao.findById(gardenerId);

        if(gardener == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Flower flower = flowerDao.findById(flowerId);

        if(flower == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        gardener.getFlowers().add(flower);
        flower.setGardener(gardener);
        gardener.setId(gardenerId);
        gardenerDao.save(gardener);
        return new ResponseEntity<>(gardener, HttpStatus.OK);
    }
}
