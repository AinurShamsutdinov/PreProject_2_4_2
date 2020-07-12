package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    public boolean addUser(User user);
    public boolean updateUserById(long id, User user);
    public boolean deleteUserById(long id);
    public boolean isUserExist(String name);
    public User getUserById(long id);
    public User getUserByName(String name);
    public List<User> getUsers();
}
