package uz.br29.appschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public HttpEntity<?> signUp(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }


}
