package org.aixposure.repository;

import org.aixposure.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin , Integer>{
    Admin findByUsername(String username);
}
