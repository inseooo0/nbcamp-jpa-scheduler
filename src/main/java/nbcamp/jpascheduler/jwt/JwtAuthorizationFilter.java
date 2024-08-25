package nbcamp.jpascheduler.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter {

    private final JwtUtil jwtUtil;

    @Before("execution(* nbcamp.jpascheduler.controller.*.*(..)) && !@annotation(nbcamp.jpascheduler.jwt.AuthenticationMethod)")
    public void AuthToken(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String token = jwtUtil.getJwtFromHeader(request);

        if (token == null) {
            throw new IllegalArgumentException("token null");
        }

        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("invalid token");

//        Claims userInfo = jwtUtil.getUserInfoFromToken(token);
//        System.out.println(userInfo.getSubject()); // username
    }

}
