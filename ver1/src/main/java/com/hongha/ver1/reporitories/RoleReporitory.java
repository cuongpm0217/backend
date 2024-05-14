package com.hongha.ver1.reporitories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.hongha.ver1.entities.Role;
import com.hongha.ver1.entities.enums.ERole;
=======
import com.hongha.ver1.entities.ERole;
import com.hongha.ver1.entities.Role;
>>>>>>> a833e23cca83a037a87c92ee39e57e07f3c32774


@Repository
public interface RoleReporitory extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    @Query(value = "select r from _role r where r.gen_id= :uuid", nativeQuery = true)
	Role findByUUID(@Param("uuid") UUID genID);
}