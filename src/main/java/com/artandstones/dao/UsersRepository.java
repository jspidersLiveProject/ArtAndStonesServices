/**
 * 
 */
package com.artandstones.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.artandstones.model.Users;

/**
 * @author gargvis
 *
 */
public interface UsersRepository extends CrudRepository<Users, String> {
	
	@Query("SELECT us.password FROM Users us WHERE us.userName = :username")
	String getPasswordByUsername(@Param("username") String username);

}
