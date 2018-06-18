/**
 * 
 */
package com.artandstones.dao;

import org.springframework.data.repository.CrudRepository;

import com.artandstones.model.Users;

/**
 * @author gargvis
 *
 */
public interface UsersRepository extends CrudRepository<Users, String> {

}
