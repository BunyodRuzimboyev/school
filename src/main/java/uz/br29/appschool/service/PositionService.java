package uz.br29.appschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.br29.appschool.dto.request.PositionAddRequest;
import uz.br29.appschool.dto.request.PositionEditRequest;
import uz.br29.appschool.dto.response.ApiResponse;
import uz.br29.appschool.entity.Position;
import uz.br29.appschool.repository.PositionRepository;
import uz.br29.appschool.security.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;
    private final SanitizeInputService sanitizeInputService;


    public HttpEntity<?> add(User currentUser, PositionAddRequest request) {

        Position position = Position.builder()
                .name(sanitizeInputService.sanitizeInput(request.getName()))
                .build();

        try {

            Position savedPosition = positionRepository.save(position);
            return ResponseEntity.status(201).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Position is saved")
                            .object(savedPosition)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within saving position !");
        }

    }

    public HttpEntity<?> edit(User currentUser, PositionEditRequest request) {

        try {

            Optional<Position> optional = positionRepository.findById(request.getId());
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .message("Position hasn't found")
                                .success(false)
                                .build()
                );
            }

            Position position = optional.get();
            if (request.getName() != null && request.getName().length() > 2) {
                position.setName(sanitizeInputService.sanitizeInput(request.getName()));
            }

            Position editedPosition = positionRepository.save(position);

            return ResponseEntity.status(202).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Position has been updated")
                            .object(editedPosition)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within updating position !");
        }

    }

    public HttpEntity<?> get(User currentUser) {

        try {

            List<Position> positionList = positionRepository.findAll();
            return ResponseEntity.status(200).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Position list")
                            .object(positionList)
                            .build()
            );
        } catch (Exception exception) {
            throw new RuntimeException("Error within getting position list !");
        }

    }

    public HttpEntity<?> get(User currentUser, UUID id) {

        try {

            Optional<Position> optional = positionRepository.findById(id);
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .success(false)
                                .message("Position hasn't been found !")
                                .build()
                );
            }
            Position position = optional.get();
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Position")
                            .success(true)
                            .object(position)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within getting position by id !");
        }
    }
}
