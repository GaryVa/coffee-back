package cl.ucm.coffee.service;


import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import cl.ucm.coffee.persitence.repository.TestimonialsRepository;
import cl.ucm.coffee.service.dto.TestimonioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialsService implements ITestimonialsService{

    @Autowired
    private TestimonialsRepository repository;

    @Override
    public TestimonialsEntity save(TestimonioDto testimonioDto) {
        TestimonialsEntity testimonialsEntity = new TestimonialsEntity();
        testimonialsEntity.setIdCoffee(testimonioDto.getIdCoffee());
        testimonialsEntity.setTestimonial(testimonioDto.getTestimonials());
        testimonialsEntity.setUsername(testimonioDto.getUsername());
        return repository.save(testimonialsEntity);
    }

    @Override
    public List<TestimonialsEntity> findByIdCoffee(Integer id) {
        return repository.findByIdCoffee(id);
    }


}
