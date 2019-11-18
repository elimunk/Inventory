package com.elimunk.openlegacyproj.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elimunk.openlegacyproj.bean.Item;
import com.elimunk.openlegacyproj.controller.ItemController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/inventory")
public class ItemApi {

	@Autowired
	private ItemController itemController;

	@PostMapping
	@ApiOperation(value = "Add item to inventory list")
	public Item addItem(@RequestBody Item item) throws Exception {
		return itemController.addItem(item);
	}

	@PutMapping("/withdrawal/{id}")
	@ApiOperation(value = "Withrawal quantity of a specific item")
	public void withdrawalQuantity(@PathVariable("id") long itemId,
			@ApiParam(value = "Withrawal amount", required = true) @RequestBody int amount) throws Exception {
		itemController.withdrawalQuantity(itemId, amount);
	}

	@PutMapping("/deposit/{id}")
	@ApiOperation(value = "Deposit quantity of a specific item")
	public void depositQuantity(@PathVariable("id") long itemId,
			@ApiParam(value = "Deposit amount", required = true) @RequestBody int amount) throws Exception {
		itemController.depositQuantity(itemId, amount);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Find one item from the inventory list by id")
	public Item getItem(@PathVariable("id") long itemId) throws Exception {
		return itemController.getItem(itemId);
	}

	@GetMapping
	@ApiOperation(value = "Return the inventory list")
	public List<Item> getAll() {
		return itemController.getAllItems();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete one item from the inventory list by id")
	public void deleteItem(@ApiParam(value = "Item no to delete", required = true) @PathVariable("id") long itemId) throws Exception {
		itemController.deleteItem(itemId);
	}

}
