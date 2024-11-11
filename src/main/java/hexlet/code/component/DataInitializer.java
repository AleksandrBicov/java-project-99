package hexlet.code.component;


import hexlet.code.repository.UserRepository;
import hexlet.code.dto.UserCreateDTO;
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
    private final UserMapper userMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var userData = new UserCreateDTO();
        userData.setFirstName("hexlet");
        userData.setLastName("HEXLET");
        userData.setEmail("hexlet@test.com");
        userData.setPassword("123");
        var user = userMapper.map(userData);
        userRepository.save(user);
    }
}
