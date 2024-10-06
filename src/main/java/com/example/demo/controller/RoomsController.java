package com.example.demo.controller;

import com.example.demo.model.Rooms;
import com.example.demo.service.RoomsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
//@CrossOrigin(origins = "http://localhost:3000")  // Re-enable CORS for frontend if needed
public class RoomsController {

    private final RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    // List all rooms
    @GetMapping
    public ResponseEntity<List<Rooms>> listRooms() {
        List<Rooms> roomsList = roomsService.getAllRooms();
        return new ResponseEntity<>(roomsList, HttpStatus.OK);  // Return list of rooms with HTTP 200 status
    }

    // Create a new room
    @PostMapping("/create")
    public ResponseEntity<Rooms> createRoom(@RequestBody Rooms room) {
        try {
            Rooms newRoom = roomsService.saveRoom(room);
            return new ResponseEntity<>(newRoom, HttpStatus.CREATED);  // Return created room with HTTP 201 status
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  // Handle any exception that occurs
        }
    }

    // View a specific room by ID
    @GetMapping("/view/{id}")
    public ResponseEntity<Rooms> viewRoom(@PathVariable Long id) {
        Optional<Rooms> optionalRoom = roomsService.getRoomById(id);
        if (optionalRoom.isPresent()) {
            return new ResponseEntity<>(optionalRoom.get(), HttpStatus.OK);  // Return room if found, with HTTP 200 status
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Return HTTP 404 if room not found
        }
    }

    // Update an existing room
    @PutMapping("/update/{id}")
    public ResponseEntity<Rooms> updateRoom(@PathVariable Long id, @RequestBody Rooms updatedRoom) {
        Optional<Rooms> optionalExistingRoom = roomsService.getRoomById(id);
        if (optionalExistingRoom.isPresent()) {
            Rooms existingRoom = optionalExistingRoom.get();
            existingRoom.setRoomName(updatedRoom.getRoomName());
            existingRoom.setDescription(updatedRoom.getDescription());
            // Optionally update other fields

            Rooms savedRoom = roomsService.saveRoom(existingRoom);
            return new ResponseEntity<>(savedRoom, HttpStatus.OK);  // Return updated room with HTTP 200 status
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Return HTTP 404 if room not found
        }
    }

    // Delete a room by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteRoom(@PathVariable Long id) {
        try {
            boolean isDeleted = roomsService.deleteRoom(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return HTTP 204 on successful deletion
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return HTTP 404 if room not found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Handle any exception that occurs
        }
    }
}
