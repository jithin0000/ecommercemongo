package com.jithin.ecommerce.controller;

import com.jithin.ecommerce.configuration.JwtTokenProvider;
import com.jithin.ecommerce.dto.JwtTokenResponse;
import com.jithin.ecommerce.dto.LoginRequestDto;
import com.jithin.ecommerce.dto.RegisterRequestDto;
import com.jithin.ecommerce.exceptions.UserRoleNotFoundException;
import com.jithin.ecommerce.model.Role;
import com.jithin.ecommerce.model.User;
import com.jithin.ecommerce.services.RoleService;
import com.jithin.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;

import static com.jithin.ecommerce.configuration.SecurityConfig.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto user) {

        User newUser = new User();
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());

        if (user.getRoles().size() > 0) {
            for (String roleName :
                    user.getRoles()) {
                Role role = roleService.getUserRoleByName(roleName);
                if (role == null) {
                    throw new UserRoleNotFoundException("no role with this name");
                }
                newUser.getRoles().add(role);
            }
        }


        return new ResponseEntity<>(userService.saveUser(newUser), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(), loginRequestDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity<>(new JwtTokenResponse(jwtToken), HttpStatus.CREATED);
    }
}
