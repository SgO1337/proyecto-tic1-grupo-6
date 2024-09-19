package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Optional;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.Users;

@Service
public class AuthService {

    @Autowired
    private UserRepository UserRepository;

    public boolean authenticate(String email, String password) throws NoSuchAlgorithmException {
        Optional<Users> UserssOpt = UserRepository.findByEmail(email);

        if (UserssOpt.isPresent()) {
            Users Userss = UserssOpt.get();
            String hashedPassword = hashPassword(password);

            // Comparar la contraseña cifrada
            return Userss.getPassword().equals(hashedPassword);
        }
        return false;
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();

        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public void registerUser(String email, String password) throws NoSuchAlgorithmException {
        String hashedPassword = hashPassword(password);
        Users newUser = new Users();
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        UserRepository.save(newUser);
    }
}
