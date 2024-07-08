package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Expense;

public interface ExpenseService {
	Expense save(Expense expenseRequest);

	Expense findById(long id);

	Expense findByUUID(UUID genId);

	List<Expense> getAll();

	Expense update(long id, Expense expenseRequest);

	boolean delete(long id);

	Expense updateByUUID(UUID genID, Expense expenseRequest);

	boolean deleteByUUID(UUID genID);

	Page<Expense> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<Expense> findByNameLike(String name, int pageNo, int pageSize, String sortBy, String sortType);
}
