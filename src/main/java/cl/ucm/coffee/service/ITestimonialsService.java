package cl.ucm.coffee.service;

import cl.ucm.coffee.persitence.entity.TestimonialsEntity;

import java.util.List;

public interface ITestimonialsService {

    TestimonialsEntity save(TestimonialsEntity testimonialsEntity);

    List<TestimonialsEntity> findByIdCoffee(Integer id);
}
