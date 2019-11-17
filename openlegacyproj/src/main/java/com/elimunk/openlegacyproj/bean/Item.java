package com.elimunk.openlegacyproj.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue
	@Column(name = "itemId")
	@ApiModelProperty(notes = "The item Nunber (Unique and Auto generated)")
	private Long itemId;

	@Column(name = "name", unique = true, nullable = false)
	@ApiModelProperty(notes = "The item name (unique)")
	private String name;

	@Column(name = "amount")
	@ApiModelProperty(notes = "Amount in Stock")
	private int amount;

	@Column(name = "inventoryCode", unique = true, nullable = false)
	@ApiModelProperty(notes = "The item barcode (unique)")
	private String inventoryCode;

	public Item() {
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", amount=" + amount + ", inventoryCode=" + inventoryCode
				+ "]";
	}

}
