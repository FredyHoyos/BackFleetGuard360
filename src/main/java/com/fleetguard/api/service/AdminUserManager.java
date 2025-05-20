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
import com.fleetguard.api.repository.AdministratorRepository;
import com.fleetguard.api.model.Administrator;

@Service
public class AdminUserManager implements UserDetailsManager {
    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails user){
        ((Administrator) user).setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user in the repository
        administratorRepository.save((Administrator) user);
    }

    @Override
    public void updateUser(UserDetails user){
        Administrator admin = (Administrator) user;
        admin.setPassword(passwordEncoder.encode(user.getPassword()));
        administratorRepository.save(admin);
    }

    @Override
    public void deleteUser(String username){
        Optional<Administrator> admin = administratorRepository.findByUsername(username);
        admin.ifPresent(administratorRepository::delete);
    }

    @Override
    public boolean userExists(String username){
        return administratorRepository.findByUsername(username).isPresent();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // You can implement this method to change the user's password
        // For example, when a user wants to change their password
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Administrator> userOptional = administratorRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format("User with username {0} not found", username));
        }
        return userOptional.get();
    }
}
