package api.builder;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.BaseApi;
import api.BaseApi.MethodType;
import api.pojo.LoginUserRequestDTO;
import api.pojo.LoginUserResponseDTO;
import io.restassured.http.ContentType;
import junit.framework.Assert;
import qumu.LoadProp;
import utils.Constants;

public class LoginUserLink extends BaseApi{
	private LoginUserResponseDTO LoginUserResponseDTO;
	private LoginUserRequestDTO LoginUserRequestDTO;
	private String requestString;
	public static String sampleRequest = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

	public LoginUserLink(String request) throws IOException {
		this.requestString = request;
		ObjectMapper objectMapper = new ObjectMapper();
		this.LoginUserRequestDTO = objectMapper.readValue(this.requestString, LoginUserRequestDTO.class);
		generateRequest();
	}

	public void generateRequest() {
		setMethod(MethodType.POST);
		setContentType(ContentType.JSON);
		setBaseUri(LoadProp.getproperty("BASE_URI"));
		setBasePath(Constants.ResourcePath.LOGIN_USER);
	}

	public LoginUserResponseDTO getResponse() {
		return this.LoginUserResponseDTO;
	}

	public void setResponse(LoginUserResponseDTO LoginUserResponseDTO) {
		this.LoginUserResponseDTO = LoginUserResponseDTO;
	}

	public void createResponse() throws IOException {
		super.setBody(this.LoginUserRequestDTO);
		ObjectMapper objectMapper = new ObjectMapper();
		String response = execute().asString();
		this.LoginUserResponseDTO = objectMapper.readValue(response, LoginUserResponseDTO.class);
	}

	public void updateRequest(String email, String password) throws IOException {
		this.LoginUserRequestDTO.setEmail(email);
		this.LoginUserRequestDTO.setPassword(password);
	}

	public void executeAssertSuccess() throws Exception {
		this.createResponse();
		Assert.assertEquals(200, this.getApiResponse().getStatusCode());
	}

}
