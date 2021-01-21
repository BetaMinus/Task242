package service;

import model.Role;

public interface RoleService {

    public Role readRole(int id);
    public void updateRole(Role role);
    public Role findRoleByName(String roleName);
    public void createRole(String roleName);
    public Role createRoleIfNotFound(String roleName);
}
