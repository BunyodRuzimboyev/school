package uz.br29.appschool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.br29.appschool.anotations.CurrentUser;
import uz.br29.appschool.dto.request.FacultyAddRequest;
import uz.br29.appschool.dto.request.FacultyEditRequest;
import uz.br29.appschool.security.User;
import uz.br29.appschool.service.FacultyService;

import java.util.UUID;

@RestController
@RequestMapping("api/school/faculty")
@RequiredArgsConstructor
@Tag(name = "Faculty")
public class FacultyController {

    private final FacultyService service;

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN, ADMIN, DIRECTOR')")
    @PostMapping("/add")
    public HttpEntity<?> add(@CurrentUser User currentUser, @Valid @RequestBody FacultyAddRequest request){
        return service.add(currentUser, request);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN, ADMIN, DIRECTOR')")
    @PutMapping("/edit")
    public HttpEntity<?> edit(@CurrentUser User currentUser, @Valid @RequestBody FacultyEditRequest request){
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