package uz.br29.appschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import uz.br29.appschool.dto.response.ApiResponse;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;


    public ApiResponse login(LoginRequest request) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);

        return ApiResponse.builder()
                .message("Bearer token is: ")
                .object(jwt)
                .success(true)
                .build();

    }

}