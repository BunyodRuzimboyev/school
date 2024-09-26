package uz.br29.appschool.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.br29.appschool.anotations.CurrentUser;
import uz.br29.appschool.security.User;
import uz.br29.appschool.security.UserMeService;

@RestController
@RequestMapping("api/user/me")
@RequiredArgsConstructor
@Tag(name = "Get current user's data")
public class UserMeController {

    private final UserMeService service;

    @GetMapping("/get")
    public HttpEntity<?> getCurrentUserData(@CurrentUser User currentUser){
        return service.getCurrentUserData(currentUser);
    }

}
