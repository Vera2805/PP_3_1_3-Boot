package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {

        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUser(id);
        entityManager.remove(user);
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> AllUsers = new HashSet<User>(entityManager.createQuery("select u from User u", User.class).getResultList());
        return AllUsers;
    }

    @Override
    public void updateUser(User user) {
        user.setRoles(getUser(user.getId()).getRoles());
        entityManager.merge(user);
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public String getPassword(Long id) {
        return entityManager.find(User.class, id).getPassword();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TypedQuery<User> typedQuery = entityManager.createQuery(
                "select u from User u LEFT JOIN FETCH u.roles where u.username = '" + username + "'", User.class
        );
        User user = typedQuery.getSingleResult();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

}
