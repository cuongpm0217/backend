package com.hongha.ver1.repositories;

import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hongha.ver1.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByGenId(UUID genId);

	Page<Account> findByCodeContaining(String code, Pageable paging);
}
