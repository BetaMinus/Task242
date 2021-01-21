package dao;
import model.User;
import java.util.List;

public interface UserDao {

    void createUser(User user);
    User readUser(long id);
    List<User> readAllUsers();
    void updateUser(User user);
    void deleteUser(User user);
    User findUserByLogin(String login);
}

