package org.store.manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.store.manager.model.Customer;
import org.store.manager.model.Product;
import org.store.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ProductController {

	ProductService productService = new ProductService();
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "products", method = RequestMethod.GET)
	public List<Product> getProducts() {
		log.debug("Logger: {}{}");
		return productService.getAllProducts();
	}
	@RequestMapping(value = "/products", params = {"productId"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody
	Product getProduct(@RequestParam(value = "productId") Long id) {
		return productService.getProduct(id);
	}
}
