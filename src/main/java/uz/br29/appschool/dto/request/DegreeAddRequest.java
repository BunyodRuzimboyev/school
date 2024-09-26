package uz.br29.appschool.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DegreeAddRequest {

    @NotBlank(message = "Degree name mustn't be empty")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Degree's price per academic hour must be bigger than 0.0")
    private Double pricePerAcademicHour;

}
