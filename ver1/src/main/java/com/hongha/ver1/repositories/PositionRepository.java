package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
	Position findByGenId(UUID genId);
	Page<Position> findByVnameLike(String vname,Pageable paging);
}
