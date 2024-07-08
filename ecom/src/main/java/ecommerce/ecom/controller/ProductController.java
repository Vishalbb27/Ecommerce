package ecommerce.ecom.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import ecommerce.ecom.dto.ProductDto;
import ecommerce.ecom.entity.Product;
import ecommerce.ecom.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<?> createProduct(@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") BigDecimal price,
			@RequestParam("rating") BigDecimal rating, @RequestParam("discount") BigDecimal discount,
			@RequestParam("categoryId") int categoryId, @RequestParam("quantity") int quantity,
			@RequestParam("file") MultipartFile file) {
		ProductDto productDto;
		try {
			productDto = new ProductDto(name, description, price, rating, discount, categoryId, quantity);
			productService.createProduct(productDto,file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Product Successfully Created", HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getProducts(){
		List<ProductDto> productDtos = productService.getAllProducts();
		return new ResponseEntity<>(productDtos,HttpStatus.OK);
	}
	
	@GetMapping("/image/{productId}")
    public ResponseEntity<?> getProductImage(@PathVariable("productId") int productId) {
        byte[] image = productService.getImgeByProductId(productId);
        	System.out.println(productId);
        	Map<String, byte[]> response = new HashMap<String, byte[]>();
        	response.put("image", image);
            return new ResponseEntity(response,HttpStatus.OK);
        }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") int productId,@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") BigDecimal price,
			@RequestParam("rating") BigDecimal rating, @RequestParam("discount") BigDecimal discount,
			@RequestParam("categoryId") int categoryId, @RequestParam("quantity") int quantity,
			@RequestParam("file") MultipartFile file){
    	
    	try {
			ProductDto productDto = new ProductDto(name, description, price, rating, discount, categoryId, quantity);
			productService.updateProduct(productId,productDto,file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Product Successfully Updated", HttpStatus.ACCEPTED);
    	
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id){
    	ProductDto productDtos = productService.getProductById(id);
		return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delteProduct(@PathVariable("id") int id){
    	productService.deleteProduct(id);
		return new ResponseEntity<>("Product Deleted Succesfully",HttpStatus.OK);
    }
}
