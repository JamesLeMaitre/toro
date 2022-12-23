package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.RoleUEA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleUEARepo extends JpaRepository<RoleUEA,Long> {

    @Query("select r from  RoleUEA r where r.rolename=:x")
    Optional<RoleUEA> findByName(@Param("x") String name);


}
