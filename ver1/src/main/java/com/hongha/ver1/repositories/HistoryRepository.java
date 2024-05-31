package com.hongha.ver1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>{

}
