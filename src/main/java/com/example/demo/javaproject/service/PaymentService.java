package com.example.demo.javaproject.service;

import com.example.demo.javaproject.entity.Payment;
import com.example.demo.javaproject.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment findById(Long id) {
        log.info("Searching for payment with id: {}", id);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Payment with id {} not found", id);
                    return new RuntimeException("Payment not found");
                });
        log.info("Payment found: {}", payment);
        return payment;
    }

    public List<Payment> findAll() {
        log.info("Retrieving all payments");
        List<Payment> payments = paymentRepository.findAll();
        log.info("Retrieved {} payments", payments.size());
        return payments;
    }

    public Payment save(Payment payment) {
        log.info("Saving payment: {}", payment);
        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment saved successfully: {}", savedPayment);
        return savedPayment;
    }

    public void deleteById(Long id) {
        log.info("Deleting payment with id: {}", id);
        paymentRepository.deleteById(id);
        log.info("Payment with id {} deleted successfully", id);
    }
}
