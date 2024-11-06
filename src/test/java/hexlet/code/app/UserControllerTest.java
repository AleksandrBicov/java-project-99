package hexlet.code.app;


import hexlet.code.app.model.User;
import hexlet.code.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(3L);
        user.setFirstName("hexlet");
        user.setLastName("hexlet");
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);
    }

    @Test
    @WithMockUser(username = "hexlet",password = "123")
    public void testIndex() throws Exception {
        when(userRepository.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is("test@example.com")));
    }

    @Test
    @WithMockUser(username = "hexlet",password = "123")
    public void testShow() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("test@example.com")));
    }


    @Test
    @WithMockUser(username = "hexlet", password = "123")
    public void testCreate() throws Exception {
        String Json = "{\"email\":\"new@example.com\",\"password\":\"newpassword\"}";

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is("new@example.com")));
    }



    @Test
    @WithMockUser(username = "hexlet", password = "123")
    public void testUpdate() throws Exception {
        String Json = "{\"email\":\"new@new.com\",\"password\":\"newpassword\"}";

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is("new@new.com")));

        Optional<User> user1 = userRepository.findByEmail("new@new.com");

        long id = user1.get().getId();
        String userUpdateJson = "{\"email\":\"updated@example.com\",\"password\":\"updatedpassword\"}";


        mockMvc.perform(put("/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userUpdateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("updated@example.com")));
    }


    @Test
    @WithMockUser(username = "hexlet",password = "123")
    public void testDelete() throws Exception {
        doNothing().when(userRepository).deleteById(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }
}