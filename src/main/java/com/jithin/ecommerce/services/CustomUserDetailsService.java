package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.User;
import com.jithin.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("no user with this name");
        }

        return user;
    }

    @Transactional
    public User loadUserById(String id) {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new UsernameNotFoundException("no user with this name");
        }
        return user;
    }
}
