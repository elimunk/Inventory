package com.elimunk.openlegacyproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.elimunk.openlegacyproj.bean.Item;
import com.elimunk.openlegacyproj.dao.IitemDao;

@Controller
public class ItemController {

	@Autowired
	private IitemDao itemDao;

	public ItemController() {
	}

	public Item addItem(Item item) throws Exception {
		validateNewItem(item);
		return itemDao.save(item);
	}

	@Transactional
	public void withdrawalQuantity(long itemId, int amount) throws Exception {
		Item item = itemDao.findById(itemId).get();
		if (item.getAmount() - amount >= 0) {
			itemDao.withdrawalQuantity(itemId, amount);
		} else {
			throw new Exception("The item amount is less than withdrawal amount.");
		}
	}

	@Transactional
	public void depositQuantity(long itemId, int amount) throws Exception {
		if (amount > 0) {
			itemDao.depositQuantity(itemId, amount);
		} else {
			throw new Exception("You can't deposit a zero or negative amount.");
		}
	}

	public Item getItem(long itemId) throws Exception {
		validateExistItemById(itemId);
		Item item = itemDao.findById(itemId).get();
		return item;
	}

	public List<Item> getAllItems() {
		List<Item> inventory = (List<Item>) itemDao.findAll();
		return inventory;
	}

	public void deleteItem(long itemId) throws Exception {
		validateExistItemById(itemId);
		itemDao.deleteById(itemId);
	}

	private void validateNewItem(Item item) throws Exception {
		if (item == null) {
			throw new Exception("Name and Inventory Code are required.");
		}
		if (item.getName() == null || item.getName().isEmpty()) {
			throw new Exception("The item must include a name.");
		}
		if (item.getInventoryCode() == null || item.getInventoryCode().isEmpty()) {
			throw new Exception("The item must include a code.");
		}
		if (itemDao.existsByName(item.getName())) {
			throw new Exception("The item name is already exist.");
		}
		if (itemDao.existsByInventoryCode(item.getInventoryCode())) {
			throw new Exception("The inventory code is already exist.");
		}
		if (item.getAmount() < 0) {
			throw new Exception("The item amount must be greater than 0.");
		}
	}

	private void validateExistItemById(long itemId) throws Exception {
		if (!itemDao.existsById(itemId)) {
			throw new Exception("Item No " + itemId + " does not exist");
		}
	}

}
