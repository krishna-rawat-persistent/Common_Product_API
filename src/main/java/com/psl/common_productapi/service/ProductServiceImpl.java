package com.psl.common_productapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.psl.common_productapi.model.Product;
import com.psl.common_productapi.util.ImageUtility;
import com.psl.common_productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Override
	public Product saveProduct(Product product) {
//		return product;
		return repository.save(product);
	}

	@Override
	public Product getProduct(Long productId) {
		final Optional<Product> prod = Optional.of(repository.getReferenceById(productId));
		return Product.builder().id(prod.get().getId())
				.title(prod.get().getTitle())
				.image(ImageUtility.decompressImage(prod.get().getImage()))
				.price(prod.get().getPrice()).build();
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> prods =  repository.findAll();
		List<Product> decompressProds = new ArrayList<>();
		for(Product p: prods){
			p.setImage(ImageUtility.decompressImage(p.getImage()));
			decompressProds.add(p);
		}
		return decompressProds;
	}

	@Override
	public Product updateProduct(Product product, Long productId) {
		final Optional<Product> prod = Optional.of(repository.getReferenceById(productId));
		if(prod!=null){
			product.setId(prod.get().getId());
			repository.save(product);
			product.setImage(ImageUtility.decompressImage(prod.get().getImage()));
			return product;
		}
		return null;
	}

	@Override
	public void deleteProduct(Long productId) {
		repository.deleteById(productId);

	}

	@Override
	public void deleteAllProducts() {
		repository.deleteAll();
	}

}
