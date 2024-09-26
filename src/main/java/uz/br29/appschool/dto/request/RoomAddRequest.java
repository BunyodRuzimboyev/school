package uz.br29.appschool.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomAddRequest {

    @NotBlank(message = "Room name mustn't be empty")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Square must be bigger than 0.0")
    private Double square;

    @Positive(message = "Stratum must be bigger than 0")
    private Integer stratum;

    @Positive(message = "Stratum must be bigger than 0")
    private Integer countChairs;

}
