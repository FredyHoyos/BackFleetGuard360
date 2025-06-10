package com.fleetguard.api.controller;

import com.fleetguard.api.DTO.DriverDTO;
import com.fleetguard.api.exception.EntityNotFoundException;
import com.fleetguard.api.mapper.DriverMapper;
import com.fleetguard.api.model.Driver;
import com.fleetguard.api.repository.DriverRepository;
import com.fleetguard.api.service.PhotoStorageService;
import org.springframework.core.io.Resource;
import jakarta.validation.Valid;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = {"http://localhost:3000", "https://front-fleet-guard360.vercel.app"})

@RestController
@RequestMapping("/api/driver")
public class DriverController {
    @Autowired
    private DriverRepository repository;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private PhotoStorageService photoStorageService;

    DriverController() {}
    DriverController(DriverRepository repository, DriverMapper driverMapper) {
        this.repository = repository;
        this.driverMapper = driverMapper;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Driver> getAll() {
        return repository.findAll();
    }

    @PostMapping("/register")
    public @ResponseBody Driver createDriver(@Valid @RequestBody DriverDTO newDriver) {
        Driver driver = driverMapper.toModel(newDriver);
        return repository.save(driver);
    }

    @PutMapping("/{id}")
    public @ResponseBody Driver updateDriver(@PathVariable int id, @Valid @RequestBody DriverDTO newDriver) {
        Driver driver = driverMapper.toModel(newDriver);
        driver.setId(Long.valueOf(id));
        return repository.save(driver);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody void deleteDriver(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<String> uploadPhoto(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try{
            Driver driver = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver not found" + id));
            String relativePath = photoStorageService.savePhoto(file, Long.valueOf(id), driver.getUsername());
            driver.setPhoto(relativePath);
            repository.save(driver);

            return ResponseEntity.ok("Photo uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while uploading photo.");
        }
    }

    @GetMapping("/{id}/photo")
    public  ResponseEntity<Resource> getPhoto(@PathVariable int id){
        Driver driver = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver not found" + id));
        if (driver.getPhoto() == null) {
            return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(driver.getPhoto());
        try {
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/{id}/path")
    public ResponseEntity<Map<String, String>> getPhotoPath(@PathVariable int id){
        Driver dirver = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver not found" + id));
        String photoPath = dirver.getPhoto();

        if(photoPath == null || photoPath.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Map<String, String> response = new HashMap<>();
        response.put("path", photoPath);

        return ResponseEntity.ok(response);
    }
}