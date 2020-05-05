package pl.coderslab.drivertips.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.model.*;
import pl.coderslab.drivertips.repositories.*;
import pl.coderslab.drivertips.services.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class StarterDataCreator implements ApplicationRunner {

    private final UserService userService;
    private final UserRepository userRepository;
    private final TipRepository tipRepository;
    private final TagRepository tagRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final TrainingRepository trainingRepository;


    public StarterDataCreator(UserService userService, UserRepository userRepository, TipRepository tipRepository, TagRepository tagRepository, AnswerRepository answerRepository, QuestionRepository questionRepository, TrainingRepository trainingRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tipRepository = tipRepository;
        this.tagRepository = tagRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.trainingRepository = trainingRepository;
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

        Question question = new Question();
        question.setText("Drowsy driving is a major hazard, especially for:");
        question.setPoints(5);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        question.setAnswers(answers);


        Training training = new Training();
        training.setTitle("Stay Alert");

        Tag tag = new Tag();
        tag.setName("Pay Attention");

        List<Question> questions = new ArrayList<>();
        questions.add(question);
        training.setQuestions(questions);

        Tip tip = new Tip();
        tip.setDate(LocalDate.now());
        tip.setTitle("Stay Alert");
        tip.setDescription("Actively pay attention to your actions and those of the drivers around you when you are driving");

        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        tip.setTags(tags);

        tip.setTraining(training);

        answer.setQuestion(question);
        question.setTraining(training);
        training.setTip(tip);

        answerRepository.save(answer);
        questionRepository.save(question);
        trainingRepository.save(training);
        tagRepository.save(tag);
        tipRepository.save(tip);

    }
}
