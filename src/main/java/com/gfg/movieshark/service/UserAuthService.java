package com.gfg.movieshark.service;


import com.gfg.movieshark.domain.User;
import com.gfg.movieshark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This service will create an implementation of a UserDetailsService Bean and same will be
 * used by spring to call get the user details from the custom implementation (here our mysql).*/

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }


    /**
     * Encoding the user password before storing in the DB.
     * Encoder bean will be provided with the one mentioned in our Security Configuration bean.
     * */
    public void addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
