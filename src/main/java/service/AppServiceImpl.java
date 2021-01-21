package service;

import dao.RoleDao;
import dao.UserDao;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppServiceImpl implements AppService, UserDetailsService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public User readUser(long id) {
        return userDao.readUser(id);
    }

    public List<User> readAllUsers() {
        return userDao.readAllUsers();
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    public Role readRole(int id) {
        return roleDao.readRole(id);
    }

    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    public Role findRoleByName(String roleName) {
        return roleDao.findRoleByName(roleName);
    }

    public void createRole(String roleName) {
        roleDao.createRole(roleName);
    }

    public Role createRoleIfNotFound(String roleName) {
        return roleDao.createRoleIfNotFound(roleName);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findUserByLogin(s);
    }
}