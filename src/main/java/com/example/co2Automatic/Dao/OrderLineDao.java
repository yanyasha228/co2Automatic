package com.example.co2Automatic.Dao;

import com.example.co2Automatic.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineDao extends JpaRepository<OrderLine, Long> {

}
