package pl.coderslab.drivertips.services;


import pl.coderslab.drivertips.model.User;

public interface UserService {
    User findByUserName(String name);

    void saveUser(User user);
}
