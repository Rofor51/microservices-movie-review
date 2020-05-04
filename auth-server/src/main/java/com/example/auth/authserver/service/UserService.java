package com.example.auth.authserver.service;


import com.example.auth.authserver.model.Role;
import com.example.auth.authserver.model.User;
import com.example.auth.authserver.repository.RoleRepository;
import com.example.auth.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;





    public User saveUser(User user) {

        user.setRoles(Collections.singleton(roleRepository.findByRole("USER")));

        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s).orElseThrow(() ->
            new UsernameNotFoundException(String.format("User could not be found")));




        return withUsername(user.getUserName())
                .password(user.getPassWord())
                .authorities(getAuthorities(user.getRoles()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();

        roles.forEach(group -> {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(group.getRole()));
        });

        return simpleGrantedAuthorities;
    }



    public Optional<User> signup(String username, String password, String email) {
        Optional<User> user = Optional.empty();
      User user1 = new User(username,password,email);
        System.out.println(password);
       saveUser(user1);
        if(!userRepository.findByUserName(username).isPresent()) {

           // user = Optional.of(userRepository.save(new User(username,passwordEncoder.encode(password),email)));



            //user.get().setRoles(Collections.singleton(roleRepository.findByRole("USER")));

        }

        return user;
    }
}
