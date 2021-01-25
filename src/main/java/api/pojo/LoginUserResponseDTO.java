package api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginUserResponseDTO {
	@JsonProperty("token")
	private String token;
	@JsonProperty("error")
	private String error;

	@JsonProperty("token")
	public String getToken() {
		return token;
	}

	@JsonProperty("token")
	public void setToken(String token) {
		this.token = token;
	}
	
	@JsonProperty("error")
	public String getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(String error) {
		this.error = error;
	}
}
