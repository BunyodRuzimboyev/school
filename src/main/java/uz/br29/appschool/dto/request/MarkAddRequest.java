package uz.br29.appschool.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MarkAddRequest {

    @NotBlank(message = "Mark name mustn't be empty")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity bigger than 0")
    private Short quantity;

}
