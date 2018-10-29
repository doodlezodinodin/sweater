package ua.nure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
