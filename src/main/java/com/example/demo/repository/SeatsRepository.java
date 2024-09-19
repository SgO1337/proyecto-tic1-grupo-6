package com.example.demo.repository;
import com.example.demo.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;//no se si estabien

public interface SeatsRepository extends JpaRepository<Seats, Long> {
    // devuelve asientos OCUPADOS por idScreening
    @Query("SELECT s FROM Seats s WHERE s.isBooked = true AND s.screening.idScreening = :idScreening")
    List<Seats> getAllBookedSeatsByScreeningId(@Param("screeningId") Long screeningId);

    // devuelve asientos DESOCUPADOS por idScreening
    @Query("SELECT s FROM Seats s WHERE s.isBooked = false AND s.screening.idScreening = :idScreening")
    List<Seats> getAllUnbookedSeatsByScreeningId(@Param("screeningId") Long screeningId);

    // Find seat by seatId and screeningId (esto sirve para luego ocuparlos/desocuparlos)
    @Query("SELECT s FROM Seats s WHERE s.idSeat = :seatId AND s.screening.idScreening = :screeningId")
    Optional<Seats> findSeatByScreeningIdAndSeatId(@Param("screeningId") Long screeningId, @Param("seatId") Long seatId);
}
