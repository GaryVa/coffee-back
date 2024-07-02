package cl.ucm.coffee.service.dto;



public interface UserDto {
    String getUsername();
    String getEmail();
    Boolean getLocked();
    Boolean getDisabled();
}
