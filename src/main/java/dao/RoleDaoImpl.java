package dao;

import model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    public Role findRoleByName(String roleName) {
        Query query = entityManager.createQuery("FROM Role where roleName=:roleName");
        query.setParameter("roleName", roleName);
        try {
            return (Role) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
