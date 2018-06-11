package org.store.manager.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private long id;
	private String description;
	private long stock;

	public Product() {

	}

	public Product(long id, String description,long stock) {
		this.id = id;
		this.description = description;
		this.stock=stock;

	}

}
