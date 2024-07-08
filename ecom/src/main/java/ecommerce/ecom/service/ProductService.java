package ecommerce.ecom.service;

import java.util.List;

import ecommerce.ecom.dto.ProductDto;

public interface ProductService {
	public void createProduct(ProductDto productDto,byte[] image);
	public void updateProduct(int id, ProductDto productDto,byte[] image);
	public List<ProductDto> getAllProducts();
	public void deleteProduct(int id);
	public void updateProductQuantitySold(int id,int quantitySold);
	public List<ProductDto> getAllProductsByCategory(int categoryId);
	public byte[] getImgeByProductId(int productId);
	public ProductDto getProductById(int productId);
}
