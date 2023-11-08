package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleRepo;

    public RoleServiceImpl(RoleDao roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Set<Role> indexRoles() {
        return roleRepo.indexRoles();

    }

    @Override
    public Role getRole(String roleName) {
        return roleRepo.getRole(roleName);
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepo.getRoleById(id);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleRepo.addRole(role);
    }

    @Override
    public Set<Role> getRolesByIds(Set<Integer> roleIds) {
        return roleRepo.getRolesByIds(roleIds);
    }

}
