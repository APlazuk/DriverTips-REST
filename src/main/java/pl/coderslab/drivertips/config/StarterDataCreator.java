package pl.coderslab.drivertips.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.domain.User;
import pl.coderslab.drivertips.repositories.UserRepository;
import pl.coderslab.drivertips.services.UserService;


@Component
public class StarterDataCreator implements ApplicationRunner {

    private final UserService userService;
    private final UserRepository userRepository;


    public StarterDataCreator(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        createUser();
    }

    private void createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");

        if (!userRepository.existsUserByUsername(user.getUsername())){
            userService.saveUser(user);
        }
    }
}
