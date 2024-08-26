package nbcamp.jpascheduler.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    INCORRECT_LOGIN_INFO(HttpStatus.UNAUTHORIZED, "Incorrect login info"),
    JWT_TOKEN_NOT_EXIT(HttpStatus.BAD_REQUEST, "Jwt token not included"),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid jwt token"),
    NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "not authorized");

    private final HttpStatus httpStatus;
    private final String message;
}
