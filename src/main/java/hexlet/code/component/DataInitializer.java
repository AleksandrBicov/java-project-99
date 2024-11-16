package hexlet.code.component;


import hexlet.code.model.TaskStatus;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TaskStatusRepository taskStatusRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var userData = new UserCreateDTO();
        userData.setFirstName("hexlet");
        userData.setLastName("HEXLET");
        userData.setEmail("hexlet@test.com");
        userData.setPassword("123");

        if (userRepository.findByEmail(userData.getEmail()).isEmpty()) {
            var user = userMapper.map(userData);
            userRepository.save(user);
        }
        // Инициализация дефолтных статусов
        taskStatusRepository.save(new TaskStatus("Draft", "draft"));
        taskStatusRepository.save(new TaskStatus("To Review", "to_review"));
        taskStatusRepository.save(new TaskStatus("To Be Fixed", "to_be_fixed"));
        taskStatusRepository.save(new TaskStatus("To Publish", "to_publish"));
        taskStatusRepository.save(new TaskStatus("Published", "published"));
    }
}
