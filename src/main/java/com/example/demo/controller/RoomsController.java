package com.example.demo.controller;

import com.example.demo.model.Rooms;
import com.example.demo.service.RoomsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Cambio a RestController para respuestas en JSON
@RequestMapping("/api/rooms")  // Cambia la URL base para empezar con /api
@CrossOrigin(origins = "http://localhost:3000")  // Permitir CORS para tu aplicación React
public class RoomsController {

    private final RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    // List all rooms
    @GetMapping
    public String listRooms(Model model) {
        List<Rooms> rooms = roomsService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "home"; // Ensure that "home.html" exists and lists the rooms
    }

    // Show the form to create a new room
    @GetMapping("/create")
    public String createRoomForm(Model model) {
        model.addAttribute("room", new Rooms());  // Add a new room object for form binding
        return "create"; // Ensure that "create.html" is for room creation
    }

    // Save a new room or updated room
    @PostMapping("/save")
    public String saveRoom(@ModelAttribute("room") Rooms room) {
        roomsService.saveRoom(room);
        return "redirect:/rooms"; // Redirect back to the list after saving
    }

    // View a specific room by ID
    @GetMapping("/view/{id}")
    public String viewRoom(@PathVariable Long id, Model model) {
        Rooms room = roomsService.getRoomById(id);
        if (room == null) {
            return "redirect:/rooms";  // Handle room not found, redirect to the list
        }
        model.addAttribute("room", room);
        return "view"; // Ensure that "view.html" exists to show room details
    }

    // Show the form to update an existing room
    @GetMapping("/update/{id}")
    public String updateRoomForm(@PathVariable Long id, Model model) {
        Rooms room = roomsService.getRoomById(id);
        if (room == null) {
            return "redirect:/rooms";  // Handle room not found, redirect to the list
        }
        model.addAttribute("room", room);
        return "update"; // Ensure that "update.html" is for editing the room
    }

    @PostMapping("/update")
    public String updateRoom(@ModelAttribute Rooms room) {
        Rooms existingRoom = roomsService.getRoomById(room.getId());
        if (existingRoom == null) {
            return "redirect:/rooms";  // Handle room not found, redirect to the list
        }

        // Update the existing room's fields
        existingRoom.setRoomName(room.getRoomName());

        // Save the updated room
        roomsService.saveRoom(existingRoom);

        return "redirect:/rooms"; // Redirect to the rooms list after update
    }

    // Delete a room
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomsService.deleteRoom(id);
        return "redirect:/rooms"; // Redirect back to the list after deletion
    }
}
