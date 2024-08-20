package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByGenId(UUID genId);

	Page<Customer> findByNameLike(String name, Pageable paging);

	Page<Customer> findByPhone1OrPhone2Like(String phone1, String phone2, Pageable paging);
}
