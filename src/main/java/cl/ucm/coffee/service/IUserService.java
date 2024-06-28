package cl.ucm.coffee.service;

import cl.ucm.coffee.persitence.entity.UserEntity;
import cl.ucm.coffee.service.dto.RegistroDto;

import java.util.List;

public interface IUserService {
    UserEntity crearUsuario(RegistroDto dto);
    List<UserEntity> listarUsuarios();

}
