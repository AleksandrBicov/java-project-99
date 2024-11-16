package hexlet.code.service;

import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.dto.user.UserDTO;
import hexlet.code.dto.user.UserUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.UserMapper;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    public UserDTO getById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        // Создаем DTO для ответа без пароля
        UserDTO responseDTO = userMapper.map(user);
        responseDTO.setPassword(null);

        return responseDTO;
    }

    public UserDTO create(UserCreateDTO userData) {
        User user = userMapper.map(userData);
        userRepository.save(user);
        // Создаем DTO для ответа без пароля
        UserDTO responseDTO = userMapper.map(user);
        responseDTO.setPassword(null);

        return responseDTO;
    }

    public UserDTO update(UserUpdateDTO userData, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        // Проверка, что пользователь может редактировать только свои данные
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (!user.getUsername().equals(currentPrincipalName)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own data.");
        } else {
            userMapper.map(userData, user);
            userRepository.save(user);
        }
        // Создаем DTO для ответа без пароля
        UserDTO responseDTO = userMapper.map(user);
        responseDTO.setPassword(null);

        return responseDTO;
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        // Проверка, что пользователь может удалять только себя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (!user.getUsername().equals(currentPrincipalName)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only delete your own data.");
        } else {
            userRepository.deleteById(id);
        }
    }
}
