package com.example.auth_api.domain.controllers;


import com.example.auth_api.domain.dto.LoginRequestDTO;
import com.example.auth_api.domain.dto.LoginResponseDTO;
import com.example.auth_api.domain.dto.RegisterRequestDTO;
import com.example.auth_api.domain.entities.Role;
import com.example.auth_api.domain.infra.security.TokenService;
import com.example.auth_api.domain.repositories.RoleRepository;
import com.example.auth_api.domain.repositories.UserRepository;
import com.example.auth_api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = this.userRepository.findByEmail(body.email());
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        if (user.isEmpty()) {
            User newUser = new User();

            newUser.setName(body.name());
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setRoles(Set.of(basicRole));

            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);

            return  ResponseEntity.ok(new LoginResponseDTO(newUser.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body) {
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found."));

        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);

            return  ResponseEntity.ok(new LoginResponseDTO(user.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }


    //Teste de rota protegida por roles
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

}



