package api.builder;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.BaseApi;
import api.pojo.GetUserListResponseDTO;
import io.restassured.http.ContentType;
import junit.framework.Assert;
import qumu.LoadProp;
import utils.Constants;

public class GetUserListLink extends BaseApi {
	public GetUserListResponseDTO getUserListResponseDTO;
	private String requestString;

	public GetUserListLink(String request) throws IOException {
		this.requestString = request;
		generateRequest();
	}

	public void generateRequest() {
		setMethod(MethodType.GET);
		setContentType(ContentType.JSON);
		setBaseUri(LoadProp.getproperty("BASE_URI"));
		setBasePath(Constants.ResourcePath.GET_USERS);
	}

	public GetUserListResponseDTO getResponse() {
		return this.getUserListResponseDTO;
	}

	public void setResponse(GetUserListResponseDTO getUserListResponseDTO) {
		this.getUserListResponseDTO = getUserListResponseDTO;
	}

	public void createResponse() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String response = execute().asString();
		this.getUserListResponseDTO = objectMapper.readValue(response, GetUserListResponseDTO.class);
	}

	public void updateRequest(String key, String value) throws IOException {
		super.addQueryParam(key, value);
	}
	
	public void executeAssertSuccess() throws Exception {
		this.createResponse();
		Assert.assertEquals(200, this.getApiResponse().getStatusCode());
	}
}
