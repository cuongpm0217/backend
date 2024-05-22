package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hongha.ver1.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	@Query(value = "select b from _account b where b._gen_id= :uuid", nativeQuery = true)
	Account findByUUID(@Param("uuid") UUID gen_id);
}
