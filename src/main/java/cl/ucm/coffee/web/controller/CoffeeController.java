package cl.ucm.coffee.web.controller;


import cl.ucm.coffee.persitence.entity.CoffeeEntity;
import cl.ucm.coffee.service.ICoffeeService;
import cl.ucm.coffee.service.dto.CoffeUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    @Autowired
    private ICoffeeService coffeeService;

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
            coffeeEntity.setImage64(foto.getBytes());

            CoffeeEntity savedCoffee = coffeeService.save(coffeeEntity);
            return ResponseEntity.ok(savedCoffee);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

    }

    @GetMapping("/coffee/buscar")
    public ResponseEntity<?> findByName(String name){
        try {
            List<CoffeeEntity> coffees = coffeeService.findByName(name);
            return ResponseEntity.ok(coffees);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/coffee/delete")
    public ResponseEntity<?> deleteCoffee(Integer id_coffee){
        try {
            boolean deleted = coffeeService.deleteCoffeeById(id_coffee);
            if(deleted) {
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.status(404).build();
            }
        } catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/coffee/update")
    public ResponseEntity<?> updateCoffee(@RequestBody CoffeUpdateDto coffeUpdateDto) {
        try {
            coffeeService.updateCoffee(coffeUpdateDto);
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

}
