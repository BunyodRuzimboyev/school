package uz.br29.appschool.security;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank(message = "Username is empty or null")
    private String username;

    @NotBlank(message = "Password is empty or null")
    private String password;

}
