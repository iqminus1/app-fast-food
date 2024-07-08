package uz.pdp.appfastfood.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResultDTO<T> implements Serializable {
    private boolean success;
    private T data;
    private String errorMessage;
    private List<FieldErrorDTO> fieldErrors;

    public static <T> ApiResultDTO<T> success(T data) {
        ApiResultDTO<T> apiResultDTO = new ApiResultDTO<>();
        apiResultDTO.setSuccess(true);
        apiResultDTO.setData(data);
        return apiResultDTO;
    }

    public static ApiResultDTO<?> error(String errorMessage) {
        ApiResultDTO<String> apiResultDTO = new ApiResultDTO<>();
        apiResultDTO.setErrorMessage(errorMessage);
        return apiResultDTO;
    }

    public static ApiResultDTO<?> errors(List<FieldErrorDTO> fieldErrors) {
        ApiResultDTO<String> apiResultDTO = new ApiResultDTO<>();
        apiResultDTO.setFieldErrors(fieldErrors);
        return apiResultDTO;
    }
}
