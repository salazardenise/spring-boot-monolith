package com.example.springbootmonolith.controllers;

import com.example.springbootmonolith.models.Song;
import com.example.springbootmonolith.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SongsController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/songs")
    public Iterable<Song> findAllUsers() {
        return songRepository.findAll();
    }

    @GetMapping("/songs/{songId}")
    public Optional<Song> findUserById(@PathVariable Long userId) {
        return songRepository.findById(userId);
    }

    @DeleteMapping("/songs/{songId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        songRepository.deleteById(userId);
        return HttpStatus.OK;
    }

    @PostMapping("/songs")
    public Song createNewUser(@RequestBody Song newUser) {
        return songRepository.save(newUser);
    }

    @PatchMapping("/songs/{songId}")
    public Song updateUserById(@PathVariable Long userId, @RequestBody Song userRequest) {

        Song songFromDb = songRepository.findById(userId).get();

        songFromDb.setTitle(userRequest.getTitle());
        songFromDb.setLength(userRequest.getLength());

        return songRepository.save(songFromDb);
    }
}
