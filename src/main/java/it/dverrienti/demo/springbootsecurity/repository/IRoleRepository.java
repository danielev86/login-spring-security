package it.dverrienti.demo.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.dverrienti.demo.springbootsecurity.repository.domain.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	
	@Query(" SELECT r FROM Role r WHERE r.roleName = :roleName")
	public Role findRole(@Param("roleName") String role);
}
