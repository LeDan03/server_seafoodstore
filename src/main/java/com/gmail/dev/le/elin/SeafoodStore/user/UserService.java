package com.gmail.dev.le.elin.SeafoodStore.user;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.gmail.dev.le.elin.SeafoodStore.exception.ResourceNotFoundException;
import com.gmail.dev.le.elin.SeafoodStore.role.Role;
import com.gmail.dev.le.elin.SeafoodStore.role.RoleRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @PostConstruct
    private void initAdminAccount() {
        if (!userRepository.existsById(1)) {
            User admin = new User();
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Admin role not found. Please ensure it is initialized."));

            admin.setUsername("ADMIN");
            admin.setPhoneNumber("0869518622");
            admin.setEmail("elin.le.dev@gmail.com");
            admin.setPassword("$2a$10$56BKnNBs19zLyLdrBc9PjeO49/JPSnBGb346MlnMPNpydwfVCa4Se");
            admin.setRole(adminRole);
            userRepository.save(admin);

            logger.info("Admin account created with phone number: " + admin.getPhoneNumber() + " and email: "
                    + admin.getEmail());
        }
    }
}
