package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;

    private String roomName;
    private String description;

    @ManyToOne(optional=false)
    @JoinColumn(name = "idBranch", nullable = false)
    private Branches branch;

    // Getters and setters
    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }
}
