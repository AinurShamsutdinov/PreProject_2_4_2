package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    public void addUser(User user);
    public void updateUserById(long id, User user);
    public void deleteUserById(long id);
    public boolean isUserExist(String name);
    public User getUserById(long id);
    public User getUserByName(String name);
    public List<User> getUsers();
}
