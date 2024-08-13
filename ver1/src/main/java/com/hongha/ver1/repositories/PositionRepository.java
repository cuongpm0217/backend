package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hongha.ver1.entities.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
	Position findByGenId(UUID genId);
	Page<Position> findByVnameLike(String vname,Pageable paging);
	@Query(value= "SELECT _p.* FROM `_position` as _p "			
			+ "WHERE "			
			+ "(SELECT CONVERT(_p.updated_at,DATE)) LIKE CONCAT('%', CONCAT(:searchText, '%')) "			
			+ "OR LOWER(_p.name) LIKE lower(concat('%', concat(:searchText, '%'))) "
			+ "OR LOWER(_p.vname) LIKE lower(concat('%', concat(:searchText, '%'))) "
			,countQuery = "SELECT count(_p.id) FROM `_position` as _p "			
					+ "WHERE "			
					+ "(SELECT CONVERT(_p.updated_at,DATE)) LIKE CONCAT('%', CONCAT(:searchText, '%')) "			
					+ "OR LOWER(_p.name) LIKE lower(concat('%', concat(:searchText, '%'))) "
					+ "OR LOWER(_p.vname) LIKE lower(concat('%', concat(:searchText, '%'))) "
			,nativeQuery = true)
	Page<Position> findBySearchText(@Param("searchText") String searchText, Pageable pageable);
}
