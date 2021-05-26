package com.onlineedu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineedu.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    public List<Question> findByTestType(int testType);
}
