package com.chwimong.project.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class SwaggerAccessInterceptor implements HandlerInterceptor {

    @Value("${swagger.allowed-ips:127.0.0.1}")
    private String[] allowedIps;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/swagger-ui") || requestURI.startsWith("/v3/api-docs")) {
            String clientIp = getClientIp(request);

            List<String> allowedIpList = Arrays.asList(allowedIps);

            if (!allowedIpList.contains(clientIp)) {
                log.warn("[SwaggerAccessInterceptor] 차단된 IP가 Swagger 접근 시도: URI={}, IP={}", requestURI, clientIp);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return false;
            }

            log.info("[SwaggerAccessInterceptor] Swagger 접근 허용: URI={}, IP={}", requestURI, clientIp);
        }

        return true;
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
