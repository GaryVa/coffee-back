package cl.ucm.coffee.web.controller;


import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import cl.ucm.coffee.service.ITestimonialsService;
import cl.ucm.coffee.service.dto.TestimonioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
public class TestimonialsController {

    @Autowired
    private ITestimonialsService testimonialsService;

    @PostMapping("/ingresar")
    public ResponseEntity<?> saveTestimonials(@RequestBody TestimonioDto testimonioDto){
        try {
            TestimonialsEntity saveTestimonials = testimonialsService.save(testimonioDto);
            return ResponseEntity.ok(saveTestimonials);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error: "+ e.getMessage());
        }

    }

    @GetMapping("/buscar")
    public ResponseEntity<?> findByIdCoffee(Integer Id_coffee){
        try{
            List<TestimonialsEntity> testimonials = testimonialsService.findByIdCoffee((Id_coffee));
            return ResponseEntity.ok(testimonials);
        } catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }
}
