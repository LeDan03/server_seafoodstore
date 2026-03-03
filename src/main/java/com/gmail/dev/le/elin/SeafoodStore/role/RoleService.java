package com.gmail.dev.le.elin.SeafoodStore.role;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    
    private final RoleRepository roleRepository;

    private Map<String, Role> roleMap;

    @PostConstruct
    private void initRoleMap() {
        List<Role> roles = roleRepository.findAll();
        roleMap = roles.stream().collect(Collectors.toMap(Role::getName, role -> role));
    }

    public Role getRoleByName(String name) {
        return roleMap.get(name);
    }

}
