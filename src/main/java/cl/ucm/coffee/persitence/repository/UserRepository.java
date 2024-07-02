package cl.ucm.coffee.persitence.repository;

import cl.ucm.coffee.persitence.entity.UserEntity;
import cl.ucm.coffee.service.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    @Query("SELECT u.username as username, u.email as email, u.locked as locked, u.disabled as disabled FROM UserEntity u")
    List<UserDto> findUser();
}
