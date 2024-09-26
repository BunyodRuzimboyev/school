package uz.br29.appschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.br29.appschool.dto.request.*;
import uz.br29.appschool.dto.response.ApiResponse;
import uz.br29.appschool.entity.Degree;
import uz.br29.appschool.entity.Mark;
import uz.br29.appschool.entity.Room;
import uz.br29.appschool.repository.RoomRepository;
import uz.br29.appschool.security.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final SanitizeInputService sanitizeInputService;


    public HttpEntity<?> add(User currentUser, RoomAddRequest request) {

        Room room = Room.builder()
                .name(sanitizeInputService.sanitizeInput(request.getName()))
                .countChairs(request.getCountChairs())
                .square(request.getSquare())
                .stratum(request.getStratum())
                .build();

        try {

            Room savedMark = roomRepository.save(room);
            return ResponseEntity.status(201).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Room has been saved")
                            .object(savedMark)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within saving room !");
        }

    }

    public HttpEntity<?> edit(User currentUser, RoomEditRequest request) {

        try {

            Optional<Room> optional = roomRepository.findById(request.getId());
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .message("Room hasn't been found")
                                .success(false)
                                .build()
                );
            }

            Room room = optional.get();
            if (request.getName() != null && request.getName().length() > 2) {
                room.setName(sanitizeInputService.sanitizeInput(request.getName()));
            }
            if (request.getCountChairs() != null && request.getCountChairs() >= 0) {
                room.setCountChairs(request.getCountChairs());
            }
            if (request.getSquare() != null && request.getSquare() >= 0) {
                room.setSquare(request.getSquare());
            }
            if (request.getStratum() != null && request.getStratum() >= 0) {
                room.setStratum(request.getStratum());
            }

            Room editedRoom = roomRepository.save(room);

            return ResponseEntity.status(202).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Room has been updated")
                            .object(editedRoom)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within updating room !");
        }

    }

    public HttpEntity<?> get(User currentUser) {

        try {

            List<Room> degreeList = roomRepository.findAll();
            return ResponseEntity.status(200).body(
                    ApiResponse.builder()
                            .success(true)
                            .message("Room list")
                            .object(degreeList)
                            .build()
            );
        } catch (Exception exception) {
            throw new RuntimeException("Error within getting room list !");
        }

    }

    public HttpEntity<?> get(User currentUser, UUID id) {

        try {

            Optional<Room> optional = roomRepository.findById(id);
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(
                        ApiResponse.builder()
                                .success(false)
                                .message("Room hasn't been found !")
                                .build()
                );
            }
            Room room = optional.get();
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Room")
                            .success(true)
                            .object(room)
                            .build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error within getting room by id !");
        }
    }


}
