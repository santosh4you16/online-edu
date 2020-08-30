package com.onlineedu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineedu.model.TestType;

@Repository
public interface TestTypeRepository extends JpaRepository<TestType, Long> {

    public List<TestType> findAll();
}
