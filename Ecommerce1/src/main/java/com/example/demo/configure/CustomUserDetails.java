package com.example.demo.configure;

import com.example.demo.enteties.Buyers;
import com.example.demo.enteties.Seller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Buyers userSign;
    private Seller seller;

    public CustomUserDetails(Seller seller) {
        this.seller = seller;
    }
    public CustomUserDetails(Buyers userSign) {
        this.userSign = userSign;
    }

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userSign.getRole());
        return List.of(simpleGrantedAuthority);

    }

    @Override
    public String getPassword() {

        return userSign.getPassword();
    }

    @Override
    public String getUsername() {
        return userSign.getUser_name();
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
