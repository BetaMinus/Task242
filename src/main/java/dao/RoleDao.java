package dao;
import model.Role;

public interface RoleDao {

    Role findRoleByName(String roleName);
}
