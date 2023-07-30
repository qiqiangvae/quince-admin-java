package org.example.quince.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author quince
 */
@Data
@AllArgsConstructor
public class Response<T> {
    private Integer code;
    private String message;
    private T data;
}
