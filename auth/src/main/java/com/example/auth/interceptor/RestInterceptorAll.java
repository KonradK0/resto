package com.example.auth.interceptor;

import com.example.auth.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestInterceptorAll implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
            throws Exception {
        try {
            HttpHeaders headers = setAuthorizationHeaders(req);
            HttpEntity<String> entity = new HttpEntity<>("headers", headers);
            User user = getUserInfoFromAuth0(entity);
            req.getSession().setAttribute("user", user);
            return true;
        } catch (Exception e){
            res.sendError(401, "401 Unauthorized");
            return false;
        }
    }

    private HttpHeaders setAuthorizationHeaders(HttpServletRequest req) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", req.getHeader("Authorization"));
        return headers;
    }

    private User getUserInfoFromAuth0(HttpEntity<String> entity) {
        RestTemplate httpRequest = new RestTemplate();
        return httpRequest.exchange(
                "https://konradk.eu.auth0.com/userinfo",
                HttpMethod.GET,
                entity,
                User.class
        ).getBody();
    }
}
