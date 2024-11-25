package br.com.albertopaim.apirest.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class HandlerResponse {
    public static ResponseEntity<Object> generateResponse (String message, HttpStatus statusCode){
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("message", message);

        return new ResponseEntity<Object>(item, statusCode);
    }
}
