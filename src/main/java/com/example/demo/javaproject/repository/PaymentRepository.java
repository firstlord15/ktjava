package com.example.demo.javaproject.repository;

import com.example.demo.javaproject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {

}