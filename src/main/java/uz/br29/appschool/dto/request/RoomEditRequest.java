package uz.br29.appschool.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RoomEditRequest {

    @NotBlank(message = "Id mustn't be null")
    private UUID id;

    private String name;

    private Double square;

    private Integer stratum;

    private Integer countChairs;

}
