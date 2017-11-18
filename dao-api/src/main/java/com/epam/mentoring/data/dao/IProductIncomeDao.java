package com.epam.mentoring.data.dao;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.ProductIncome;

public interface IProductIncomeDao {
	ProductIncome getProductIncomeById(Integer id) throws DataAccessException;
	Integer addProductIncome(ProductIncome productIncome) throws DataAccessException;
}