package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	Expense findByGenId(UUID genId);
	Page<Expense> findByNameLike(String name,Pageable paging);
}
