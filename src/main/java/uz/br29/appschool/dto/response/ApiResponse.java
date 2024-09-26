package uz.br29.appschool.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse {

    private String message;
    private Object object;
    private Boolean success;
}
