package cl.ucm.coffee.service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String username;
    private Boolean locked;
    private Boolean disabled;
}
