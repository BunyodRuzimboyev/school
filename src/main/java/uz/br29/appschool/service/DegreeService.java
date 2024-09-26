package uz.br29.appschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.br29.appschool.dto.request.DegreeAddRequest;
import uz.br29.appschool.dto.request.DegreeEditRequest;
import uz.br29.appschool.dto.response.ApiResponse;
import uz.br29.appschool.entity.Degree;
import uz.br29.appschool.repository.DegreeRepository;
import uz.br29.appschool.security.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DegreeService {

    private final DegreeRepository degreeRepository;
    private final SanitizeInputService sanitizeInputService;


    public HttpEntity<?> add(User currentUser, DegreeAddRequest request) {

        Degree degree = Degree.builder()
                .name(sanitizeInputService.sanitizeInput(request.getName()))
                .pricePerAcademicHour(request.getPricePerAcademicHour())
                .build();

        try {

            Degree savedDegree = degreeRepository.save(degree);
            return ResponseEntity.status(201).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Degree has been saved")
                            .object(savedDegree)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within saving degree !");
        }

    }

    public HttpEntity<?> edit(User currentUser, DegreeEditRequest request) {

        try {

            Optional<Degree> optional = degreeRepository.findById(request.getId());
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .message("Degree hasn't been found")
                                .success(false)
                                .build()
                );
            }

            Degree degree = optional.get();
            if (request.getName() != null && request.getName().length() > 2) {
                degree.setName(sanitizeInputService.sanitizeInput(request.getName()));
            }
            if (request.getPricePerAcademicHour() != null && request.getPricePerAcademicHour() >= 0) {
                degree.setPricePerAcademicHour(request.getPricePerAcademicHour());
            }

            Degree editedDegree = degreeRepository.save(degree);

            return ResponseEntity.status(202).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Degree has been updated")
                            .object(editedDegree)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within updating degree !");
        }

    }

    public HttpEntity<?> get(User currentUser) {

        try {

            List<Degree> degreeList = degreeRepository.findAll();
            return ResponseEntity.status(200).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Degree list")
                            .object(degreeList)
                            .build()
            );
        } catch (Exception exception) {
            throw new RuntimeException("Error within getting degree list !");
        }

    }

    public HttpEntity<?> get(User currentUser, UUID id) {

        try {

            Optional<Degree> optional = degreeRepository.findById(id);
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .success(false)
                                .message("Degree hasn't been found !")
                                .build()
                );
            }
            Degree degree = optional.get();
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Degree")
                            .success(true)
                            .object(degree)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within getting degree by id !");
        }
    }


}
