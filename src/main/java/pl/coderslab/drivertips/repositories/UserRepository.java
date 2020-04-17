package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.model.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);

    boolean existsUserByUsername(String username);
}
