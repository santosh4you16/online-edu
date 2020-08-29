package com.onlineedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineedu.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
