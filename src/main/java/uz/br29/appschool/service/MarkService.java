package uz.br29.appschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.br29.appschool.dto.request.MarkAddRequest;
import uz.br29.appschool.dto.request.MarkEditRequest;
import uz.br29.appschool.dto.response.ApiResponse;
import uz.br29.appschool.entity.Mark;
import uz.br29.appschool.repository.MarkRepository;
import uz.br29.appschool.security.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarkService {

    private final MarkRepository markRepository;
    private final SanitizeInputService sanitizeInputService;


    public HttpEntity<?> add(User currentUser, MarkAddRequest request) {

        Mark mark = Mark.builder()
                .name(sanitizeInputService.sanitizeInput(request.getName()))
                .quantity(request.getQuantity())
                .build();

        try {

            Mark savedMark = markRepository.save(mark);
            return ResponseEntity.status(201).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Mark has been saved")
                            .object(savedMark)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within saving mark !");
        }

    }

    public HttpEntity<?> edit(User currentUser, MarkEditRequest request) {

        try {

            Optional<Mark> optional = markRepository.findById(request.getId());
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .message("Mark hasn't been found")
                                .success(false)
                                .build()
                );
            }

            Mark mark = optional.get();
            if (request.getName() != null && request.getName().length() > 2) {
                mark.setName(sanitizeInputService.sanitizeInput(request.getName()));
            }
            if (request.getQuantity() != null && request.getQuantity() >= 0) {
                mark.setQuantity(request.getQuantity());
            }

            Mark editedMark = markRepository.save(mark);

            return ResponseEntity.status(202).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Mark has been updated")
                            .object(editedMark)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within updating mark !");
        }

    }

    public HttpEntity<?> get(User currentUser) {

        try {

            List<Mark> markList = markRepository.findAll();
            return ResponseEntity.status(200).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Mark list")
                            .object(markList)
                            .build()
            );
        } catch (Exception exception) {
            throw new RuntimeException("Error within getting mark list !");
        }

    }

    public HttpEntity<?> get(User currentUser, UUID id) {

        try {

            Optional<Mark> optional = markRepository.findById(id);
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .success(false)
                                .message("Mark hasn't been found !")
                                .build()
                );
            }
            Mark mark = optional.get();
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Mark")
                            .success(true)
                            .object(mark)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within getting mark by id !");
        }
    }


}
