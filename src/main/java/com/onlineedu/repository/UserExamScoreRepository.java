package com.onlineedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineedu.model.UserExamScore;

@Repository
public interface UserExamScoreRepository extends JpaRepository<UserExamScore, Long> {

}
