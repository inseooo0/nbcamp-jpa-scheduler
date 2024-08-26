package nbcamp.jpascheduler.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nbcamp.jpascheduler.domain.UserRole;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static nbcamp.jpascheduler.jwt.JwtUtil.AUTHORIZATION_KEY;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter {

    private final JwtUtil jwtUtil;

    @Aspect
    @Component
    @Order(1)
    public class commonAuthenticationAspect {
        @Before("execution(* nbcamp.jpascheduler.controller.*.*(..)) && !@annotation(nbcamp.jpascheduler.jwt.AuthorizationMethod)")
        public void AuthToken(JoinPoint joinPoint) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest();
            String token = jwtUtil.getJwtFromHeader(request);

            if (token == null) {
                throw new IllegalArgumentException("token null");
            }

            if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("invalid token");
        }
    }

    @Aspect
    @Component
    @Order(2)
    public class adminAuthenticationAspect {
        @Before("@annotation(nbcamp.jpascheduler.jwt.AdminAuthenticationMethod)")
        public void AuthToken(JoinPoint joinPoint) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest();
            String token = jwtUtil.getJwtFromHeader(request);
            Claims userInfo = jwtUtil.getUserInfoFromToken(token);

            if (!userInfo.get(AUTHORIZATION_KEY).equals("ADMIN")) {
                throw new IllegalArgumentException("not admin");
            }
        }
    }

}
