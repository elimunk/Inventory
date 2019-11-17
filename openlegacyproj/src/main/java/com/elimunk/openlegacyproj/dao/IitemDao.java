package com.elimunk.openlegacyproj.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.elimunk.openlegacyproj.bean.Item;

public interface IitemDao extends CrudRepository<Item, Long> {

	public boolean existsByName(String name);
	
	public boolean existsByInventoryCode(String name);

	@Modifying
	@Query("UPDATE Item SET amount= amount - :amount WHERE itemId = :itemId")
	public void withdrawalQuantity(@Param("itemId") long itemId, @Param("amount") int amount);

	@Modifying
	@Query("UPDATE Item SET amount= amount + :amount WHERE itemId = :itemId")
	public void depositQuantity(@Param("itemId") long itemId, @Param("amount") int amount);

}
