package cl.ucm.coffee.web.controller;


import cl.ucm.coffee.persitence.entity.CoffeeEntity;
import cl.ucm.coffee.service.ICoffeeService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    @Autowired
    private ICoffeeService coffeeService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, String>> coffes(){
        Map map = new HashMap();
        map.put("coffee", "Coffees :Get)");
        return ResponseEntity.ok(map);
    }
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> coffe(){
        Map map = new HashMap();
        map.put("coffee", "Coffees Post:)");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/coffees")
    public ResponseEntity<?> coffees(){
        return ResponseEntity.ok(coffeeService.getCoffees());
    }

    @PostMapping("/coffees")
    public ResponseEntity<?> saveCoffees(@RequestParam(name="name") String name,
                                         @RequestParam(name="price") Integer price,
                                         @RequestParam(name="desc")String description,
                                         @RequestParam(name="foto") MultipartFile foto){
        try {
            CoffeeEntity coffeeEntity = new CoffeeEntity();
            coffeeEntity.setName(name);
            coffeeEntity.setPrice(price);
            coffeeEntity.setDescription(description);
            coffeeEntity.setImage64(Base64.getEncoder().encodeToString(foto.getBytes()));

            CoffeeEntity savedCoffee = coffeeService.save(coffeeEntity);
            return ResponseEntity.ok(savedCoffee);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

    }

    @GetMapping("/coffee/buscar")
    public ResponseEntity<?> findByName(@RequestParam(name="name") String name){
        try {
            List<CoffeeEntity> coffees = coffeeService.findByName(name);
            return ResponseEntity.ok(coffees);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/coffee/delete")
    public ResponseEntity<?> deleteCoffee(@RequestParam(name="id_coffee") Integer id_coffee){
        try {
            boolean deleted = coffeeService.deleteCoffeeById(id_coffee);
            if(deleted) {
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.status(404).build();
            }
        } catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/coffee/update")
    public ResponseEntity<?> updateCoffee(@RequestParam(name="id_coffee") Integer id_coffee,
                                          @RequestParam(name="name") String name,
                                          @RequestParam(name="price") Integer price,
                                          @RequestParam(name="desc")String description,
                                          @RequestParam(name="foto") MultipartFile foto) {
        try {
            CoffeeEntity updatedCoffee = new CoffeeEntity();
            updatedCoffee.setName(name);
            updatedCoffee.setPrice(price);
            updatedCoffee.setDescription(description);
            updatedCoffee.setImage64(Base64.getEncoder().encodeToString(foto.getBytes()));

            Optional<CoffeeEntity> resultado = coffeeService.updateCoffee(id_coffee, updatedCoffee);
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

}
