package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
	Partner findByGenId(UUID genId);
	Page<Partner> findByPhone1OrPhone2Like(String phone1,String phone2,Pageable paging);
}
