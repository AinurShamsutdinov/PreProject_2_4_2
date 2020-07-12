package web.DAO;

import web.model.User;

import java.util.List;

public interface UserDao {
    public boolean addUser(User user);
    public boolean updateUserById(long id, User user);
    public boolean deleteUserById(long id);
    public boolean isUserExist(String name);
    public User getUserByName(String name);
    public User getUserById(long id);
    public List<User> getUsersList();
}
