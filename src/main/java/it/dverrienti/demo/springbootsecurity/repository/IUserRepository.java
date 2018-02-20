package it.dverrienti.demo.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.dverrienti.demo.springbootsecurity.repository.domain.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	
	@Query(" SELECT user FROM User user WHERE user.username = :username")
	User findByUsername(@Param("username") String username);

}
