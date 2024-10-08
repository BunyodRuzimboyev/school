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
public class MarkAddRequest {

    @NotBlank(message = "Mark name mustn't be empty")
    private String name;

    @Positive(message = "Quantity must be bigger than 0")
    private Short quantity;

}
