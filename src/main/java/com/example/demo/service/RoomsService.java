package com.example.demo.service;

import com.example.demo.model.Rooms;
import com.example.demo.repository.RoomsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {

    private final RoomsRepository roomsRepository;

    public RoomsService(RoomsRepository roomRepository) {
        this.roomsRepository = roomRepository;
    }

    public List<Rooms> getAllRooms() {
        return roomsRepository.findAll();
    }

    public Rooms getRoomById(Long id) {
        return roomsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveRoom(Rooms room) {
        roomsRepository.save(room);
    }

    @Transactional
    public void deleteRoom(Long id) {
        roomsRepository.deleteById(id);
    }
}
