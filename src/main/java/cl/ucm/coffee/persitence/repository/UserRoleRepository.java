package cl.ucm.coffee.persitence.repository;

import cl.ucm.coffee.persitence.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {
    UserRoleEntity findByUsername(String username);
}
