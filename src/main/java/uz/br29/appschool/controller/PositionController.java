package uz.br29.appschool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.br29.appschool.anotations.CurrentUser;
import uz.br29.appschool.dto.request.PositionAddRequest;
import uz.br29.appschool.dto.request.PositionEditRequest;
import uz.br29.appschool.security.User;
import uz.br29.appschool.service.PositionService;

import java.util.UUID;

@RestController
@RequestMapping("api/school/position")
@RequiredArgsConstructor
@Tag(name = "Position")
public class PositionController {

    private final PositionService service;

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN, ADMIN, DIRECTOR')")
    @PostMapping("/add")
    public HttpEntity<?> add(@CurrentUser User currentUser, @RequestBody PositionAddRequest request){
        return service.add(currentUser, request);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN, ADMIN, DIRECTOR')")
    @PutMapping("/edit")
    public HttpEntity<?> edit(@CurrentUser User currentUser, @RequestBody PositionEditRequest request){
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