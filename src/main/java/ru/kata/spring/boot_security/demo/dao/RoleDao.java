package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> indexRoles();
    Role getRole(String roleName);
    Role getRoleById(int id);
    void addRole(Role role);
    Set<Role> getRolesByIds(Set<Integer> roleIds);
}
