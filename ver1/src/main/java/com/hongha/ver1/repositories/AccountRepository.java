package com.hongha.ver1.repositories;

import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hongha.ver1.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByGenId(UUID genId);

	@Query(value= "SELECT * FROM _account a "
			+ "WHERE "
			+ "(SELECT CONVERT(a.updated_at,DATE)) LIKE CONCAT('%', CONCAT(:searchText, '%')) "
			+ "OR LOWER(a.code) LIKE lower(concat('%', concat(:searchText, '%'))) "
			+ "OR LOWER(a.name) LIKE lower(concat('%', concat(:searchText, '%'))) ",
			countQuery = "SELECT count(a.id) FROM _account a WHERE "
					+ "LOWER(a.code) LIKE lower(concat('%', concat(:searchText, '%'))) "
					+ "OR LOWER(a.name) LIKE lower(concat('%', concat(:searchText, '%'))) ",
			nativeQuery = true)
	Page<Account> findBySearchText(@Param("searchText") String searchText, Pageable pageable);
}
