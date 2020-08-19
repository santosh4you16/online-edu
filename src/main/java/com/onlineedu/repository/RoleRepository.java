package com.onlineedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlineedu.model.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    public RoleModel findById(long id);
}
