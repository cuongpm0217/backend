package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	Currency findByGenId(UUID genId);

	Currency findByCode(String code);

	Page<Currency> findByCodeContaining(String code, Pageable pageable);
}
