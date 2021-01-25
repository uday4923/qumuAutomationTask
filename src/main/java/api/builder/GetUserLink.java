package api.builder;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.BaseApi;
import api.pojo.GetUserResponseDTO;
import io.restassured.http.ContentType;
import junit.framework.Assert;
import qumu.LoadProp;
import utils.Constants;

public class GetUserLink extends BaseApi {
	public GetUserResponseDTO getUserResponseDTO;
	private String requestString;

	public GetUserLink(String request) throws IOException {
		this.requestString = request;
		generateRequest();
	}

	public void generateRequest() {
		setMethod(MethodType.GET);
		setContentType(ContentType.JSON);
		setBaseUri(LoadProp.getproperty("BASE_URI"));
		setBasePath(Constants.ResourcePath.GET_USER);
	}

	public GetUserResponseDTO getResponse() {
		return this.getUserResponseDTO;
	}

	public void setResponse(GetUserResponseDTO getUserResponseDTO) {
		this.getUserResponseDTO = getUserResponseDTO;
	}

	public void createResponse() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String response = execute().asString();
		this.getUserResponseDTO = objectMapper.readValue(response, GetUserResponseDTO.class);
	}

	public void updateRequest(String key, String value) throws IOException {
		super.addPathParam(key, value);
	}
	
	public void updateRequestQuery(String key, String value) throws IOException {
		super.addQueryParam(key, value);
	}
	
	public void executeAssertSuccess() throws Exception {
		this.createResponse();
		Assert.assertEquals(200, this.getApiResponse().getStatusCode());
	}
}
