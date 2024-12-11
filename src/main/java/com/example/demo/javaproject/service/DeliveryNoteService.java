package com.example.demo.javaproject.service;

import com.example.demo.javaproject.entity.DeliveryNote;
import com.example.demo.javaproject.repository.DeliveryNoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DeliveryNoteService {
    private final DeliveryNoteRepository deliveryNoteRepository;

    public DeliveryNoteService(DeliveryNoteRepository deliveryNoteRepository) {
        this.deliveryNoteRepository = deliveryNoteRepository;
    }

    public DeliveryNote findById(Long id) {
        log.info("Searching for delivery note with id: {}", id);
        DeliveryNote deliveryNote = deliveryNoteRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Delivery note with id {} not found", id);
                    return new RuntimeException("Delivery note not found");
                });
        log.info("Delivery note found: {}", deliveryNote);
        return deliveryNote;
    }

    public List<DeliveryNote> findAll() {
        log.info("Retrieving all delivery notes");
        List<DeliveryNote> deliveryNotes = deliveryNoteRepository.findAll();
        log.info("Retrieved {} delivery notes", deliveryNotes.size());
        return deliveryNotes;
    }

    public DeliveryNote save(DeliveryNote deliveryNote) {
        log.info("Saving delivery note: {}", deliveryNote);
        DeliveryNote savedDeliveryNote = deliveryNoteRepository.save(deliveryNote);
        log.info("Delivery note saved successfully: {}", savedDeliveryNote);
        return savedDeliveryNote;
    }

    public void deleteById(Long id) {
        log.info("Deleting delivery note with id: {}", id);
        deliveryNoteRepository.deleteById(id);
        log.info("Delivery note with id {} deleted successfully", id);
    }
}