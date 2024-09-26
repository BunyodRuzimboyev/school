package uz.br29.appschool.dto.response;

import jakarta.persistence.*;
import lombok.*;
import uz.br29.appschool.enums.Role;
import uz.br29.appschool.enums.UserType;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMeResponse {

    private UUID id;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;

    private String lastName;

    private String middleName;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
