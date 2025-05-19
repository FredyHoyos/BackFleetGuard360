package com.fleetguard.api.service;

import java.text.MessageFormat;
import java.util.Optional;

import com.fleetguard.api.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import com.fleetguard.api.repository.DriverRepository;

@Service
public class DriverUserManager implements UserDetailsManager {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails user){
        ((Driver) user).setPassword(passwordEncoder.encode(user.getPassword()));
        driverRepository.save((Driver) user);
    }

    @Override
    public void updateUser(UserDetails user){
        Driver driver = (Driver) user;
        driver.setPassword(passwordEncoder.encode(user.getPassword()));
        driverRepository.save(driver);
    }

    @Override
    public void deleteUser(String username){
        Optional<Driver> driver = driverRepository.findByUsername(username);
        driver.ifPresent(driverRepository::delete);
    }

    @Override
    public boolean userExists(String username){
        return driverRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException{
        Optional<Driver> userOptional = driverRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format("User with username {0} not found", username));
        }
        return userOptional.get();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword){
        // Finished this method
    }
}
