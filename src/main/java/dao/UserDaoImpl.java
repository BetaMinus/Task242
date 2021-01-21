package dao;
import model.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    public void createUser(User user) {
        entityManager.persist(user);
    }

    public User readUser(long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> readAllUsers() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public void deleteUser(User user) {
        entityManager.remove(entityManager.merge(user));
    }

    @Override
    public User findUserByLogin(String login) {
        Query query = entityManager.createQuery("from User where login=:login");
        query.setParameter("login", login);
        return (User) query.getSingleResult();
    }
}