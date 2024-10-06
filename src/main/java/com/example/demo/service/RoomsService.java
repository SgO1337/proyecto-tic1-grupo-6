package com.example.demo.service;

import com.example.demo.model.Branches;
import com.example.demo.model.Rooms;
import com.example.demo.repository.RoomsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomsService {

    private final RoomsRepository roomsRepository;

    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    // Get all rooms
    public List<Rooms> getAllRooms() {
        return roomsRepository.findAll();
    }

    // Get room by ID
    public Optional<Rooms> getRoomById(Long id) {
        return roomsRepository.findById(id);  // Return Optional to handle room not found
    }

    // Save a new or existing room
    @Transactional
    public Rooms saveRoom(Rooms room) {
        return roomsRepository.save(room);  // Return the saved room
    }

    public Rooms createRoom(String roomName, String description, Branches branch) {
        Rooms room = new Rooms();
        room.setRoomName(roomName);
        room.setDescription(description);
        room.setBranch(branch);
        return roomsRepository.save(room);
    }
    // Delete a room by ID
    @Transactional
    public boolean deleteRoom(Long id) {
        if (roomsRepository.existsById(id)) {  // Check if room exists before deletion
            roomsRepository.deleteById(id);
            return true;  // Return true if deletion was successful
        }
        return false;  // Return false if room was not found
    }

    // Check if room exists by ID
    public boolean existsById(Long id) {
        return roomsRepository.existsById(id);  // Check for existence
    }


}
