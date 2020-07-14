package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        if(!isUserExist(user.getName())){
            userDao.addUser(user);
        }
    }

    @Override
    public void updateUserById(long id, User user) {
        if(!isUserExist(user.getName())){
            userDao.updateUserById(id, user);
        }
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public boolean isUserExist(String name){
        return userDao.isUserExist(name);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name){
        return userDao.getUserByName(name);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsersList();
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.getUserByName(name);
        if (user != null){
            return user;
        } else {
            throw new UsernameNotFoundException(String.format("Username %s not found", name))  ;
        }
    }
}
