package com.gl.student.tracker.lab6.security;

import com.gl.student.tracker.lab6.model.Role;
import com.gl.student.tracker.lab6.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is the implementation class of {@link UserDetails} interface.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
public class AppUserDetails implements UserDetails {
    private final User user;

    public AppUserDetails(User user) {
        this.user = user;
    }

    /**
     * This method is responsible to retrieve list of authorities from the database.
     *
     * @return Instance of {@link Collection} interface.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
