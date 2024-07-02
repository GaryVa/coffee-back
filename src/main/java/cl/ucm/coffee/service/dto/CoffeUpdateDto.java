package cl.ucm.coffee.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoffeUpdateDto {
    private int id_coffee;
    private String description;
    private String name;
    private int price;
}
