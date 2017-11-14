package com.epam.mentoring.data.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.util.mappers.SupplierRowMapper;
import com.epam.mentoring.data.util.mappers.SuppliersResultSetExtractor;

public class SupplierDaoImpl implements ISupplierDao {

	@Value("${supplier.get.by_id}")
	private String getSupplierById;
	
	@Value("${supplier.get.all}")
	private String getAllSuppliersSql;
	
	@Value("${supplier.add}")
	private String addSupplierSql;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SupplierRowMapper supplierRowMapper;
	
	@Autowired
	private SuppliersResultSetExtractor suppliersResultSetExtractor;
	
	public SupplierDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Supplier getSupplierById(int id) throws DataAccessException {
		Supplier supplier = jdbcTemplate.queryForObject(getSupplierById, new Object[] {id}, supplierRowMapper);
		return supplier;
	}

	@Override
	public List<Supplier> getAllSuppliers() throws DataAccessException {
		return jdbcTemplate.query(getAllSuppliersSql, suppliersResultSetExtractor);
	}

	@Override
	public int addSupplier(Supplier supplier) throws DataAccessException {
		Assert.notNull(supplier, "No supplier provided for saving");
		return jdbcTemplate.update(addSupplierSql, supplier.getName(), supplier.getDetails());
	}

	@Override
	public int updateSupplier(Supplier supplier) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSupplier(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
