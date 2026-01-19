package com.cydeo.entity.common;

import com.cydeo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {  //Implemented so that we can implement the methods inside it
                                                     //gets the Authorities, username & password and answers 4 boolean ()-It has 7 abstract ()

    private User user;                                                 //This is the Entity User which is in our DB

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //SimpleGrantedAuthority is the impl class of GrantedAuthority Interface.
        List<GrantedAuthority> authorityList = new ArrayList<>();     //preparing a container for permissions. SP never works with Raw Strings
        GrantedAuthority authority = new SimpleGrantedAuthority(this.user.getRole().getDescription());   //String wrapped into Auth. object
        authorityList.add(authority);                                                                    //Added to the List of GrantedAuthority
        return authorityList;                                                                            //Returned List of GrantedAuthority
    }

    @Override
    public String getPassword() {
        return this.user.getPassWord();   //how I can acccess to password field of the user object
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
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
        return this.user.isEnabled();
    }

    public Long getId(){
        return this.user.getId();
    }


}