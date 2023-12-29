package demo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import demo.security.models.Users;

public interface UsersRepository extends JpaRepository<Users,Long > {
        
        UserDetails findByLogin(String login);



}
