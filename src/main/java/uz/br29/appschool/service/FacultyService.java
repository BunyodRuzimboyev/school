package uz.br29.appschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.br29.appschool.dto.request.FacultyAddRequest;
import uz.br29.appschool.dto.request.FacultyEditRequest;
import uz.br29.appschool.dto.response.ApiResponse;
import uz.br29.appschool.entity.Degree;
import uz.br29.appschool.entity.Faculty;
import uz.br29.appschool.repository.FacultyRepository;
import uz.br29.appschool.security.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final SanitizeInputService sanitizeInputService;


    public HttpEntity<?> add(User currentUser, FacultyAddRequest request) {

        Faculty faculty = Faculty.builder()
                .name(sanitizeInputService.sanitizeInput(request.getName()))
                .description(sanitizeInputService.sanitizeInput(request.getDescription()))
                .build();

        try {

            Faculty savedFaculty = facultyRepository.save(faculty);
            return ResponseEntity.status(201).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Faculty has been saved")
                            .object(savedFaculty)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within saving faculty !");
        }

    }

    public HttpEntity<?> edit(User currentUser, FacultyEditRequest request) {

        try {

            Optional<Faculty> optional = facultyRepository.findById(request.getId());
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .message("Faculty hasn't been found")
                                .success(false)
                                .build()
                );
            }

            Faculty faculty = optional.get();
            if (request.getName() != null && request.getName().length() > 2) {
                faculty.setName(sanitizeInputService.sanitizeInput(request.getName()));
            }
            if (request.getDescription() != null && request.getDescription().length() > 2) {
                faculty.setDescription(sanitizeInputService.sanitizeInput(request.getDescription()));
            }

            Faculty editedFaculty = facultyRepository.save(faculty);

            return ResponseEntity.status(202).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Faculty has been updated")
                            .object(editedFaculty)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within updating faculty !");
        }

    }

    public HttpEntity<?> get(User currentUser) {

        try {

            List<Faculty> facultyList = facultyRepository.findAll();
            return ResponseEntity.status(200).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Faculty list")
                            .object(facultyList)
                            .build()
            );
        } catch (Exception exception) {
            throw new RuntimeException("Error within getting faculty list !");
        }

    }

    public HttpEntity<?> get(User currentUser, UUID id) {

        try {

            Optional<Faculty> optional = facultyRepository.findById(id);
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .success(false)
                                .message("Faculty hasn't been found !")
                                .build()
                );
            }
            Faculty faculty = optional.get();
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Faculty")
                            .success(true)
                            .object(faculty)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within getting faculty by id !");
        }
    }


}
