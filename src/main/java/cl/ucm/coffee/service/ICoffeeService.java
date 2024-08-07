package cl.ucm.coffee.service;

import cl.ucm.coffee.persitence.entity.CoffeeEntity;
import cl.ucm.coffee.service.dto.CoffeUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ICoffeeService {

    List<CoffeeEntity> getCoffees();
    CoffeeEntity save(CoffeeEntity coffeeEntity);
    List<CoffeeEntity> findByName(String name);
    boolean deleteCoffeeById(Integer id);
    Optional<CoffeeEntity> updateCoffee(CoffeUpdateDto coffeUpdateDto);
}
