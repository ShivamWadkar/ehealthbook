package com.ehealthbook.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiResponse<T> {

    private int httpStatus;
    private boolean error;
    private T data;

    // Factory methods for standard responses
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), false, data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(HttpStatus.CREATED.value(), false, data);
    }

    public static <T> ApiResponse<T> error(HttpStatus status, T data) {
        return new ApiResponse<>(status.value(), true, data);
    }
}
