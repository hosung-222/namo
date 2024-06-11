package com.namo.spring.application.external.global.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.namo.spring.application.external.global.utils.JwtUtils;
import com.namo.spring.core.common.code.status.ErrorStatus;
import com.namo.spring.core.common.exception.AuthException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

	private final JwtUtils jwtUtils;
	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		try {
			Long userId = jwtUtils.resolveRequest(request);
			validateLogout(jwtUtils.getAccessToken(request));
			request.setAttribute("userId", userId);
			return true;
		} catch (AuthException e) {
			Gson gson = new Gson();
			String exception = gson.toJson(e.getErrorReasonHttpStatus());
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(403);
			response.getWriter().print(exception);
			return false;
		}
	}

	private void validateLogout(String accessToken) {
		String blackToken = redisTemplate.opsForValue().get(accessToken);
		if (StringUtils.hasText(blackToken)) {
			throw new AuthException(ErrorStatus.LOGOUT_ERROR);
		}
	}
}
