package uz.br29.appschool.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class FacultyEditRequest {

    @NotBlank(message = "Id mustn't be null")
    private UUID id;

    private String name;

    private String description;

}
