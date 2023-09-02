package hello.logic.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class SimpleJsonResponse {
    private final int code;
    private final String message;

}
