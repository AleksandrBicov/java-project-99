package hexlet.code.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
//    @JsonIgnore
    private String password;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
