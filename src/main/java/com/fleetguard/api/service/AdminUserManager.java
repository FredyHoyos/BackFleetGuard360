package com.fleetguard.api.service;

import java.text.MessageFormat;
import java.util.Optional;

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
    public void createUser(UserDetails admin){
        ((Administrator) admin).setPassword(passwordEncoder.encode(admin.getPassword()));
        // Save the user in the repository
        administratorRepository.save((Administrator) admin);
    }

    @Override
    public void updateUser(UserDetails user) {
        // You can implement this method to update user details if needed
        // For example, updating roles or other user information
    }

    @Override
    public void deleteUser(String username) {
        // You can implement this method to delete a user by username
        // Typically used when a user wants to delete their account
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // You can implement this method to change the user's password
        // For example, when a user wants to change their password
    }

    @Override
    public boolean userExists(String username) {

        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the repository by username
        Optional<Administrator> userOptional = administratorRepository.findByUsername(username);

        // Check if the user exists
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format("User with username {0} not found", username));
        }

        // Return the UserDetails extracted from the User entity
        return userOptional.get();
    }
}
