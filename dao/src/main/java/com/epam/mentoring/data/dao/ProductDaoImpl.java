package com.epam.mentoring.data.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.util.Assert;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.util.mappers.ProductRowMapper;
import com.epam.mentoring.data.util.mappers.ProductsWithQuantitiesResultSetExtractor;

public class ProductDaoImpl implements IProductDao{
	
	@Value("${product.get.by_id}")
	private String GET_PRODUCT_BY_ID_SQL;
	
	@Value("${product.get.all}")
	private String GET_ALL_PRODUCTS_SQL;
	
	@Value("${product.add}")
	private String ADD_PRODUCT_SQL;
	
	@Value("${product.update}")
	private String UPDATE_PRODUCT_SQL;
	
	@Value("${product.delete}")
	private String DELETE_PRODUCT_SQL;
	
	@Value("${product.quantity.get.by_id}")
	private String GET_PRODUCT_QUANTITY_BY_ID_SQL;
	
	@Value("${product.quantity.get.all}")
	private String GET_ALL_PRODUCTS_WITH_QUANTITIES_SQL;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ProductRowMapper productRowMapper;
	
	@Autowired
	ProductsWithQuantitiesResultSetExtractor productsWithQuantitiesMapper;
	
	public ProductDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Product getProductById(Integer id) {
		Product product = jdbcTemplate.queryForObject(GET_PRODUCT_BY_ID_SQL, new Object[] {id}, productRowMapper);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addProduct(Product product) {
		return jdbcTemplate.update(ADD_PRODUCT_SQL, product.getProductName(), product.getPrice(), product.getType().getId());
	}

	@Override
	public int updateProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getProductQuantityById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Product, Integer> getAllProductsWithQuantities() {
		Map<Product, Integer> map = jdbcTemplate.query(GET_ALL_PRODUCTS_WITH_QUANTITIES_SQL, productsWithQuantitiesMapper);
		return map;
	}


}