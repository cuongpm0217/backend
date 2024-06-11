package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	@Query(value = "select b from _expense b where b._gen_id= :uuid", nativeQuery = true)
	Expense findByUUID(@Param("uuid") UUID gen_id);

}
