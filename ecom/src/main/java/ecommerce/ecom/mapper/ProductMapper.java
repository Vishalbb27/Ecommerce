package ecommerce.ecom.mapper;

import ecommerce.ecom.dto.ProductDto;
import ecommerce.ecom.entity.Category;
import ecommerce.ecom.entity.Product;

public class ProductMapper {
	public static ProductDto mapToProductDto(Product product) {
		ProductDto productDto = new ProductDto(product.getName(),product.getDescription(),product.getPrice(),product.getRating(),product.getDiscount(),product.getCategory().getCategoryId(),product.getQuantity());
		productDto.setProductId(product.getProductId());
		return productDto;
	}
	
	public static Product mapToProduct(ProductDto productDto,Category category,byte[] image) {
		Product product = new Product(productDto.getName(),productDto.getDescription(),productDto.getPrice(),productDto.getRating(),productDto.getDiscount(),category,productDto.getQuantity(),image);
		return product;
	}
}
