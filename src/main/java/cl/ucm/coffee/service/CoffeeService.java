package cl.ucm.coffee.service;

import cl.ucm.coffee.persitence.entity.CoffeeEntity;
import cl.ucm.coffee.persitence.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService implements ICoffeeService{

    @Autowired
    private CoffeeRepository repository;

    @Override
    public List<CoffeeEntity> getCoffees() {
        return repository.findAll();
    }

    @Override
    public CoffeeEntity save(CoffeeEntity coffeeEntity) {
        return repository.save(coffeeEntity);
    }

    @Override
    public List<CoffeeEntity> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public boolean deleteCoffeeById(Integer id) {
        Optional<CoffeeEntity> coffeeEntity = repository.findById(id);
        if(coffeeEntity.isPresent()){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Optional<CoffeeEntity> updateCoffee(Integer id, CoffeeEntity updatedCoffee) {
        Optional<CoffeeEntity> existeCoffee = repository.findById(id);
        if (existeCoffee.isPresent()){
            CoffeeEntity coffeeEntity = existeCoffee.get();
            coffeeEntity.setName(updatedCoffee.getName());
            coffeeEntity.setPrice(updatedCoffee.getPrice());
            coffeeEntity.setDescription(updatedCoffee.getDescription());
            coffeeEntity.setImage64(updatedCoffee.getImage64());
            return Optional.of(repository.save(coffeeEntity));
        }else {
            return Optional.empty();
        }
    }
}
