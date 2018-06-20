/**
 * 
 */
package com.artandstones.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.artandstones.model.MarbelDetails;

/**
 * @author gargvis
 *
 */
public interface MarbelDetailsRepository extends CrudRepository<MarbelDetails, String> {
	@Query("select md.marbelPrice from MarbelDetails md where md.marbelType = :type")
	String getMarblePriceByType(@Param("type") String type);
}
