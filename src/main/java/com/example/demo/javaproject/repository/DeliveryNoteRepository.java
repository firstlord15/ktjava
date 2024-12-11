package com.example.demo.javaproject.repository;

import com.example.demo.javaproject.entity.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryNoteRepository  extends JpaRepository<DeliveryNote, Long> {

}