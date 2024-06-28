package cl.ucm.coffee.service;


import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import cl.ucm.coffee.persitence.repository.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialsService implements ITestimonialsService{

    @Autowired
    private TestimonialsRepository repository;

    @Override
    public TestimonialsEntity save(TestimonialsEntity testimonialsEntity) {
        return repository.save(testimonialsEntity);
    }

    @Override
    public List<TestimonialsEntity> findByIdCoffee(Integer id) {
        return repository.findByIdCoffee(id);
    }


}
