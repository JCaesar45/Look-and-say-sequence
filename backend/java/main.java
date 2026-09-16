package com.aurum.sequence;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class SequenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SequenceApplication.class, args);
    }

    public record SequenceRequest(
            @NotNull
            @Pattern(regexp = "\\d+")
            String value
    ) {}

    @PostMapping("/api/look-and-say")
    public ResponseEntity<Map<String, String>> lookAndSay(
            @Valid @RequestBody SequenceRequest request
    ) {
        return ResponseEntity.ok(Map.of(
                "input", request.value(),
                "output", transform(request.value())
        ));
    }

    private static String transform(String value) {
        StringBuilder output = new StringBuilder();
        int index = 0;

        while (index < value.length()) {
            char digit = value.charAt(index);
            int nextIndex = index + 1;

            while (nextIndex < value.length() && value.charAt(nextIndex) == digit) {
                nextIndex += 1;
            }

            output.append(nextIndex - index).append(digit);
            index = nextIndex;
        }

        return output.toString();
    }
}
