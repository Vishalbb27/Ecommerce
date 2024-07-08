package ecommerce.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ecommerce.ecom.dto.ProductDto;
import ecommerce.ecom.entity.Category;
import ecommerce.ecom.entity.Product;
import ecommerce.ecom.exception.ProductExeption;
import ecommerce.ecom.mapper.ProductMapper;
import ecommerce.ecom.repository.CategoryRepository;
import ecommerce.ecom.repository.ProductRepository;
import ecommerce.ecom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		super();
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void createProduct(ProductDto productDto,byte[] image) {
		Category category = categoryRepository.findById(productDto.getCategory()).get();
		Product product = ProductMapper.mapToProduct(productDto, category,image);
		productRepository.save(product);
	}

	@Override
	public void updateProduct(int id, ProductDto productDto,byte[] image) {
		Category category = categoryRepository.findById(productDto.getCategory()).get();
		Product product = ProductMapper.mapToProduct(productDto, category,image);
		product.setProductId(id);
		productRepository.save(product);

	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return mapFromProductToProductDto(products);
	}

	@Override
	public void deleteProduct(int id) {
		getProductById(id);
		productRepository.deleteById(id);

	}

	@Override
	public void updateProductQuantitySold(int id, int quantitySold) {
		Product product = productRepository.findById(id).stream().findFirst().orElse(null);
		if(product==null) {
			throw new ProductExeption("Product not found.");
		};
		int soldQuantity = product.getQuantity_sold();
		product.setQuantity_sold(quantitySold+soldQuantity);
		productRepository.save(product);
	}

	@Override
	public List<ProductDto> getAllProductsByCategory(int categoryId) {
		Category category = categoryRepository.findById(categoryId).get();
		List<Product> products = productRepository.findByCategory(category);
		return mapFromProductToProductDto(products);
	}
	
	private List<ProductDto> mapFromProductToProductDto(List<Product> products) {
		return products.stream().map(product -> ProductMapper.mapToProductDto(product)).collect(Collectors.toList());
	}
	
	public ProductDto getProductById(int id) {
		Product product = productRepository.findById(id).stream().findFirst().orElse(null);
		if(product==null) {
			throw new ProductExeption("Product not found.");
		};
		return ProductMapper.mapToProductDto(product);
	}
	
	@Override
	public byte[] getImgeByProductId(int productId) {
       Product product = productRepository.findById(productId).get();
        return product.getImage();
    }

}
