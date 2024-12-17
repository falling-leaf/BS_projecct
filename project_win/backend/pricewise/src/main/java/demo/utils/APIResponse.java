package demo.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse {
    private String message;
    private int status_code;
    private Object payload;

    public APIResponse(String message, int status_code, Object payload) {
        this.message = message;
        this.status_code = status_code;
        this.payload = payload;
    }

    public APIResponse(String message, int status_code) {
        this.message = message;
        this.status_code = status_code;
    }
}
