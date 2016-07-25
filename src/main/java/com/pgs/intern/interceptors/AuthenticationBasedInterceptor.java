package com.pgs.intern.interceptors;

import com.pgs.intern.services.CurrentUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lschiffer on 7/25/2016.
 */
@Component
public class AuthenticationBasedInterceptor extends HandlerInterceptorAdapter {
    @Inject
    private CurrentUser currentUser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (currentUser.isAuthenticated()) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
