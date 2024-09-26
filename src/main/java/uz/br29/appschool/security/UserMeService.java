package uz.br29.appschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.br29.appschool.dto.response.ApiResponse;
import uz.br29.appschool.dto.response.UserMeResponse;

@Service
@RequiredArgsConstructor
public class UserMeService {

    private final UserRepository userRepository;


    public HttpEntity<?> getCurrentUserData(User currentUser) {

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Your dates")
                        .object(UserMeResponse.builder()
                                .id(currentUser.getId())
                                .username(currentUser.getUsername())
                                .role(currentUser.getRole())
                                .userType(currentUser.getUserType())
                                .firstName(currentUser.getFirstName())
                                .lastName(currentUser.getLastName())
                                .middleName(currentUser.getMiddleName())
                                .build()
                        )
                        .build()
        );
    }
}
