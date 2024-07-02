package cl.ucm.coffee.service;

import cl.ucm.coffee.persitence.entity.UserEntity;
import cl.ucm.coffee.service.dto.RegistroDto;
import cl.ucm.coffee.service.dto.UserDto;
import cl.ucm.coffee.service.dto.UserUpdateDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserEntity crearUsuario(RegistroDto dto);

    List<UserDto> listarUsuarios();

    Optional<UserEntity> updateUser(UserUpdateDto userUpdateDto);

}
