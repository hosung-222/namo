package com.namo.spring.application.external.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequest {
	private UserRequest() {
		throw new IllegalStateException("Utility class");
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Schema(description = "소셜 회원가입 요청 DTO")
	public static class SocialSignUpDto {
		@Schema(description = "소셜 로그인을 통해 발급받은 accessToken", example = "exampleAccessToken")
		@NotBlank(message = "accessToken은 필수값입니다.")
		private String accessToken;
		@Schema(description = "소셜 로그인을 통해 발급받은 refreshToken", example = "exampleRefreshToken")
		@NotBlank(message = "refreshToken은 필수값입니다.")
		private String socialRefreshToken;
	}

	@Getter
	@Schema(description = "애플 회원가입 요청 DTO")
	public static class AppleSignUpDto {
		@Schema(description = "애플 로그인을 통해 발급받은 authorizationCode", example = "exampleAuthorizationCode")
		@NotBlank(message = "authorizationCode은 필수값입니다.")
		private String authorizationCode;
		@Schema(description = "애플 로그인을 통해 발급받은 identityToken", example = "exampleIdentityToken")
		@NotBlank(message = "identityToken은 필수값입니다.")
		private String identityToken;
		@Schema(description = "애플 로그인을 통해 발급받은 email", example = "exampleRefreshToken")
		private String email;
		@Schema(description = "애플 로그인을 통해 발급받은 username", example = "exampleUsername")
		private String username;
	}

	@NoArgsConstructor
	public static class TermDto {
		@NotNull
		private boolean isCheckTermOfUse;
		@NotNull
		private boolean isCheckPersonalInformationCollection;

		public boolean getIsCheckTermOfUse() {
			return isCheckTermOfUse;
		}

		public boolean getIsCheckPersonalInformationCollection() {
			return isCheckPersonalInformationCollection;
		}
	}

}
