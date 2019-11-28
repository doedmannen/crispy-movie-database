package com.example.demo.configs;

import com.example.demo.entities.DbUser;
import com.example.demo.repos.DbUserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Set;

@Configuration
public class MyUserDetailService implements UserDetailsService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Bean
    public BCryptPasswordEncoder getEncoder() { return encoder; }

    @Autowired
    private DbUserRepo repository;

    @PostConstruct
    private void createDefaultUsers(){
        if (repository.findDistinctFirstByUsernameIgnoreCase("user") == null) {
            addUser("user", "password");
        }
        if (repository.findDistinctFirstByUsernameIgnoreCase("admin") == null) {
            addAdmin("admin", "password");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DbUser user = repository.findDistinctFirstByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(user);
    }

    public void addUser(String name, String password){
        DbUser u = new DbUser(name, getEncoder().encode(password));
        try {
            repository.save(u);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addAdmin(String name, String password){
        DbUser u = new DbUser(name, getEncoder().encode(password));
        u.setRoles(Set.of("ADMIN", "USER"));
        try {
            repository.save(u);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private UserDetails toUserDetails(DbUser user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().stream().toArray(String[]::new)).build();
    }

    public static UserDetails currentUserDetails(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return principal instanceof UserDetails ? (UserDetails) principal : null;
        }
        return null;
    }

}
