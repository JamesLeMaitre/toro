package com.torocommunication.torofull.repo;


import com.torocommunication.torofull.entities.Postuler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulerRepo extends JpaRepository<Postuler,Long> {
}
