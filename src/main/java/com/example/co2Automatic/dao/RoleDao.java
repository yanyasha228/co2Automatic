package com.example.co2Automatic.dao;


import com.example.co2Automatic.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
