package com.example.demo.service;

import com.example.demo.model.Screenings;
import com.example.demo.repository.ScreeningRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import jakarta.transaction.Transactional;


@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<Screenings> getAllScreenings() {
        return screeningRepository.findAll();
    }

    public Screenings getScreeningById(Long id) {
        return screeningRepository.findById(id).orElse(null);
    }

    @Transactional
    public Screenings saveScreening(Screenings screening) {
       return screeningRepository.save(screening);
    }

    @Transactional
    public void deleteScreening(Long id) {
        screeningRepository.deleteById(id);
    }


}
