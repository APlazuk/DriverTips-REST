package pl.coderslab.drivertips.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.model.*;
import pl.coderslab.drivertips.repositories.UserRepository;
import pl.coderslab.drivertips.services.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        createTipWithFullTraining();
    }

    private void createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");

        if (!userRepository.existsUserByUsername(user.getUsername())){
            userService.saveUser(user);
        }
    }

    private void createTipWithFullTraining(){

        Answer answer = new Answer();
        answer.setText("long-distance truckers");
        answer.setCorrect(true);

        Answer answer1 = new Answer();
        answer.setText("delivery people");
        answer.setCorrect(false);

        Question question = new Question();
        question.setText("Drowsy driving is a major hazard, especially for:");
        question.setPoints(5);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        answers.add(answer1);
        question.setAnswers(answers);


        Training training = new Training();
        training.setTitle("Stay Alert");

        Tag tag = new Tag();
        tag.setName("Pay Attention");

        List<Question> questions = new ArrayList<>();
        questions.add(question);
        training.setQuestions(questions);

        Tip tip = new Tip();
        tip.setTitle("Stay Alert");
        tip.setDescription("Actively pay attention to your actions and those of the drivers around you when you are driving");

        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        tip.setTags(tags);

        tip.setTraining(training);


        training.setTip(tip);
        question.setTraining(training);
        answer.setQuestion(question);

    }
}
