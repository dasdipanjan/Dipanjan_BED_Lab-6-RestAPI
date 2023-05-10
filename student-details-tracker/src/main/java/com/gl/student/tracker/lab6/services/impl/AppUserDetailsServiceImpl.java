package com.gl.student.tracker.lab6.services.impl;

import com.gl.student.tracker.lab6.model.User;
import com.gl.student.tracker.lab6.repository.UserRepository;
import com.gl.student.tracker.lab6.security.AppUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This class is the implementation class of {@link UserDetailsService} interface.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
@Slf4j
public class AppUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public AppUserDetailsServiceImpl() {

    }

    /**
     * This method is responsible to load user information by using username from database.
     *
     * @param username String value.
     * @return Object of {@link UserDetails}
     * @throws UsernameNotFoundException when there is no user available.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new AppUserDetails(user);
    }
}
