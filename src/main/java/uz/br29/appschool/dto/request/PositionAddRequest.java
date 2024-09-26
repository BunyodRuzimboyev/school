package uz.br29.appschool.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PositionAddRequest {

    @NotBlank(message = "Position name mustn't be empty")
    private String name;

}
