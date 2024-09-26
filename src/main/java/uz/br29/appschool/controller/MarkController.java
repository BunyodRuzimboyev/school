package uz.br29.appschool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.br29.appschool.anotations.CurrentUser;
import uz.br29.appschool.dto.request.MarkAddRequest;
import uz.br29.appschool.dto.request.MarkEditRequest;
import uz.br29.appschool.security.User;
import uz.br29.appschool.service.MarkService;

import java.util.UUID;

@RestController
@RequestMapping("api/school/mark")
@RequiredArgsConstructor
@Tag(name = "Mark")
public class MarkController {

    private final MarkService service;

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN, ADMIN, DIRECTOR')")
    @PostMapping("/add")
    public HttpEntity<?> add(@CurrentUser User currentUser, @Valid @RequestBody MarkAddRequest request){
        return service.add(currentUser, request);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN, ADMIN, DIRECTOR')")
    @PutMapping("/edit")
    public HttpEntity<?> edit(@CurrentUser User currentUser, @Valid @RequestBody MarkEditRequest request){
        return service.edit(currentUser, request);
    }

    @GetMapping("/get")
    public HttpEntity<?> get(@CurrentUser User currentUser){
        return service.get(currentUser);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@CurrentUser User currentUser, @PathVariable UUID id){
        return service.get(currentUser, id);
    }


}