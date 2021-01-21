package dao;
import model.Role;

public interface RoleDao {

    void createRole(String roleName);
    Role readRole(int id);
    void updateRole(Role role);

    Role findRoleByName(String roleName);
    Role createRoleIfNotFound(String roleName);
}
