package api.builder;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.BaseApi;
import api.BaseApi.MethodType;
import api.pojo.CreateUserRequestDTO;
import api.pojo.CreateUserResponseDTO;
import api.pojo.GetUserResponseDTO;
import io.restassured.http.ContentType;
import junit.framework.Assert;
import qumu.LoadProp;
import utils.Constants;

public class CreateUserLink extends BaseApi{
	private CreateUserResponseDTO createUserResponseDTO;
	private CreateUserRequestDTO createUserRequestDTO;
	private String requestString;
	public static String sampleRequest = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

	public CreateUserLink(String request) throws IOException {
		this.requestString = request;
		ObjectMapper objectMapper = new ObjectMapper();
		this.createUserRequestDTO = objectMapper.readValue(this.requestString, CreateUserRequestDTO.class);
		generateRequest();
	}

	public void generateRequest() {
		setMethod(MethodType.POST);
		setContentType(ContentType.JSON);
		setBaseUri(LoadProp.getproperty("BASE_URI"));
		setBasePath(Constants.ResourcePath.CREATE_USER);
	}

	public CreateUserResponseDTO getResponse() {
		return this.createUserResponseDTO;
	}

	public void setResponse(CreateUserResponseDTO createUserResponseDTO) {
		this.createUserResponseDTO = createUserResponseDTO;
	}

	public void createResponse() throws IOException {
		super.setBody(this.createUserRequestDTO);
		ObjectMapper objectMapper = new ObjectMapper();
		String response = execute().asString();
		this.createUserResponseDTO = objectMapper.readValue(response, CreateUserResponseDTO.class);
	}

	public void updateRequest(String name, String job) throws IOException {
		this.createUserRequestDTO.setName(name);
		this.createUserRequestDTO.setJob(job);
	}
	
	public void executeAssertSuccess() throws Exception {
		this.createResponse();
		Assert.assertEquals(200, this.getApiResponse().getStatusCode());
	}

}
