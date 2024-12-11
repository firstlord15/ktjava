package com.example.demo.javaproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Table(name = "Delivery_Note")
@Entity
public class DeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String deliveryNumber;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    @ManyToOne
    private Invoice invoice;

    public DeliveryNote() {}
}