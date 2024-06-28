package cl.ucm.coffee.service;

import cl.ucm.coffee.persitence.entity.UserEntity;
import cl.ucm.coffee.persitence.entity.UserRoleEntity;
import cl.ucm.coffee.persitence.repository.UserRepository;
import cl.ucm.coffee.persitence.repository.UserRoleRepository;
import cl.ucm.coffee.service.dto.RegistroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity crearUsuario(RegistroDto registroDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registroDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        userEntity.setEmail(registroDto.getEmail());
        userEntity.setLocked(false);
        userEntity.setDisabled(false);

        userRepository.save(userEntity);

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUsername(registroDto.getUsername());
        userRoleEntity.setRole("CLIENT");
        userRoleEntity.setUser(userEntity);
        userRoleEntity.setGrantedDate(LocalDateTime.now());
        userRoleRepository.save(userRoleEntity);

        return userEntity;
    }

    @Override
    public List<UserEntity> listarUsuarios() {
        return userRepository.findAll();
    }
}