package com.example.namo2.global.feignClient.apple;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AppleResponse {
	private AppleResponse() {
		throw new IllegalStateException("Utility class");
	}

	@Getter
	@Setter
	public static class ApplePublicKeyDto {
		private String kty;
		private String kid;
		private String use;
		private String alg;
		private String n;
		private String e;
	}

	@Getter
	@Setter
	public static class ApplePublicKeyListDto {
		private List<ApplePublicKeyDto> keys;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GetAccessToken{
		@JsonProperty("access_token")
		private String accessToken;
		@JsonProperty("expires_in")
		private Long expiresIn;
		@JsonProperty("id_token")
		private String idToken;
		@JsonProperty("refresh_token")
		private String refreshToken;
		@JsonProperty("token_type")
		private String tokenType;
	}
}
