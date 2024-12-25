package com.example.test.service.servicelmp.auth;

import com.example.test.dto.AuthenticationRequest;
import com.example.test.dto.RegisterRequest;
import com.example.test.entity.Cart;
import com.example.test.entity.Role;
import com.example.test.entity.Costumer;
import com.example.test.exeption.ApiRequestException;
import com.example.test.repository.CartRepository;
import com.example.test.repository.CostumerRepo;
import com.example.test.security.AuthenticationResponce;
import com.example.test.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private  final CostumerRepo costumerRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CartRepository cartRepository;


    public AuthenticationResponce authenticated (AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        var user = costumerRepo.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new ApiRequestException("opps is not found"));

        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponce.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponce register(RegisterRequest registerRequest) {
        var user = Costumer.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))

                .role(registerRequest.getEmail().equalsIgnoreCase("admin@admin.com") ? Role.ADMIN : Role.USER)
                .build();
        costumerRepo.save(user);

        Cart cart = new Cart();
        cart.setCostumer(user);
        cart.setStatus("DESACTIVER");

        cartRepository.save(cart);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponce.builder()
                .token(jwtToken)
                .build();
    }

}
