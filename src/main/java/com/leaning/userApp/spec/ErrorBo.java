package com.leaning.userApp.spec;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author rajatha.kunj
 */
@Getter
@Setter
@NoArgsConstructor
public class ErrorBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code = null;

    private String message = null;

    private String originalMessage = null;

    public ErrorBo(String code, String message, String originalMessage) {
        this.code = code;
        this.message = message;
        this.originalMessage = originalMessage;
    }


    @Override
    public String toString() {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("message", message);
        return response.toString();
    }

    public String getExceptionMessage() {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("message", message);
        response.put("details", originalMessage);
        return response.toString();
    }

    public interface ErrorMessages {
        String getValue();
    }
}