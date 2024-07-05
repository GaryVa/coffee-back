package cl.ucm.coffee.service;

import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import cl.ucm.coffee.service.dto.TestimonioDto;

import java.util.List;

public interface ITestimonialsService {

    TestimonialsEntity save(TestimonioDto testimonioDto);

    List<TestimonialsEntity> findByIdCoffee(Integer id);
}
