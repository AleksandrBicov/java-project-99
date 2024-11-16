package hexlet.code.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateDTO {


    private String firstName;

    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;
}
