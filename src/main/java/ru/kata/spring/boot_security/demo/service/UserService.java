package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Set;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    void deleteUser(Long id);

    Set<User> getAllUsers();

    void updateUser(User user);

    User getUser(Long id);

    String getPassword(Long id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void updateRole(String updateRoleForm);

    void updateRole(Role r);
}
