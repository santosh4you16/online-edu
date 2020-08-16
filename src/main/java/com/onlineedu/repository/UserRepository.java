package com.onlineedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlineedu.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

  UserModel findByUsername(String username);
}
