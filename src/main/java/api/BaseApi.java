package api;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.Reporter;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
	
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	private MethodType method;
	private Object body;
	private ContentType contentType;
	private String baseUri;
	private Map<String, Object> queryParams = new HashMap<>();
	private Map<String, Object> pathParams = new HashMap<>();
	private Map<String, Object> formURLEncoded = new HashMap<>();
	private Map<String, Object> params = new HashMap<>();
	private String basePath;
	private String cookie;
    private Map<String, Object> headers = new HashMap<>();
    private Response response;
    private String authName;
    private String authPassword;

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}
	
	public RequestSpecBuilder getSpec() {
		return this.requestSpecBuilder;
	}

	public String getAuthPassword() {
		return authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public enum MethodType {
		POST, GET, PUT, DELETE, PATCH
	}

	public MethodType getMethod() {
		return method;
	}

	public void setMethod(MethodType method) {
		this.method = method;
	}

    public void setBody(Object obj) {
	    this.body = obj;
        requestSpecBuilder.setBody(obj);
    }

    public Object getBody(){
	    return this.body;
    }

    public void setContentType(ContentType contentType){
	    this.contentType = contentType;
	    requestSpecBuilder.setContentType(contentType);
    }

    public ContentType getContentType(){
	    return this.contentType;
    }

    public void setBaseUri(String baseUri){
	    this.baseUri = baseUri;
	    requestSpecBuilder.setBaseUri(baseUri);
    }

    public String getBaseUri(){
	    return this.baseUri;
    }

	public void setCookie(String cookie){
		this.cookie = cookie;
		requestSpecBuilder.addCookie(cookie);
	}

	public String getCookie(){
		return this.cookie;
	}

    public void setBasePath(String basePath){
        this.basePath = basePath;
        requestSpecBuilder.setBasePath(basePath);
    }

    public String getBasePath(){
        return this.basePath;
    }

    public void addHeaders(Map<String, String> headers){
        this.headers.putAll(headers);
        requestSpecBuilder.addHeaders(headers);
    }

    public void addHeader(String headerKey, String headerValue){
		this.headers.put(headerKey,headerValue);
		requestSpecBuilder.addHeader(headerKey,headerValue);
	}

    public Map<String, Object> getHeaders(){
        return this.headers;
    }


    public void addQueryParam(String paramKey, Object paramValue){
	    this.queryParams.put(paramKey,paramValue);
	    requestSpecBuilder.addQueryParam(paramKey,paramValue);
    }

    public void addQueryParams(Map<String, String> queryParams){
	    this.queryParams.putAll(queryParams);
	    requestSpecBuilder.addQueryParams(queryParams);
    }
    

    public void addPathParam(String paramKey, Object paramValue){
	    this.pathParams.put(paramKey,paramValue);
	    requestSpecBuilder.addPathParam(paramKey,paramValue);
    }

    public Map<String, Object> getQueryParams(){
	    return this.queryParams;
    }


	public void addFormURLEncoded(String paramKey, Object paramValue){
		this.formURLEncoded.put(paramKey,paramValue);
		requestSpecBuilder.addFormParam(paramKey,paramValue);
	}

	public void addFormURLEncoded(Map<String, Object> formURLEncoded){
		this.formURLEncoded.putAll(formURLEncoded);
		requestSpecBuilder.addFormParams(formURLEncoded);
	}

	public Map<String, Object> getFormURLEncoded(){
		return this.formURLEncoded;
	}

	public void addParam(String paramKey, Object paramValue){
		this.params.put(paramKey,paramValue);
		requestSpecBuilder.addParam(paramKey,paramValue);
	}

	public void addParams(Map<String, String> queryParams){
		this.params.putAll(queryParams);
		requestSpecBuilder.addParams(queryParams);
	}

	public Map<String, Object> getParams(){
		return this.params;
	}

	public RequestSpecBuilder getRequestSpecBuilder() {
		return requestSpecBuilder;
	}

	public Response execute() {
		RequestSpecification requestSpecification = requestSpecBuilder.build();
		Response response;
		RestAssuredConfig config = new RestAssuredConfig();
		if(this.authName!=null && this.authPassword!=null){
			PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
			basicAuth.setUserName(this.authName);
			basicAuth.setPassword(this.authPassword);
			requestSpecBuilder.setAuth(basicAuth);
		}
		RestAssured.defaultParser = Parser.JSON;
		switch (method) {
		case GET:
			response = given().config(config).spec(requestSpecification).when().log().all().get().then().log().all().extract().response();
			break;
		case POST:
			response = given().config(config).spec(requestSpecification).when().log().all().post().then().log().all().extract().response();
			break;
		case PUT:
			response = given().config(config).spec(requestSpecification).when().log().all().put().then().log().all().extract().response();
			break;
		case DELETE:
			response = given().config(config).spec(requestSpecification).when().log().all().delete().then().log().all().extract().response();
			break;
		case PATCH:
			response = given().config(config).spec(requestSpecification).when().log().all().patch().then().log().all().extract().response();
			break;
		default:
			throw new RuntimeException("API method not specified");

		}
		this.response=response;
		//printResponse(response);
		return response;
	}

	private void printResponse(Response response) {
		String contentType = response.contentType();

		if (contentType.toLowerCase().contains("text/html")) {
			final DateFormat timeFormat = new SimpleDateFormat("MM.dd.yyyy HH-mm-ss");
			final String fileName = Reporter.getCurrentTestResult().getMethod().getMethodName() + "_"
					+ timeFormat.format(new Date()) + ".html";

			String outputDir = Reporter.getCurrentTestResult().getTestContext().getOutputDirectory();
			outputDir = outputDir.substring(0, outputDir.lastIndexOf(File.separator)) + "/html";

			File file = new File(outputDir + File.separator + fileName);
			if (file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}
			PrintWriter writer = null;

			try {
				file.createNewFile();
				writer = new PrintWriter(file);
				writer.write(response.asString());
				writer.flush();
				Reporter.log("<a href=\"" + fileName + "\" target=\"_blank\"><b>API Response</b></a><br>");
			} catch (Throwable e) {
				throw new RuntimeException(e);
			} finally {
				writer.close();
			}
		} else {
			Reporter.log("<br>" + "API Response:" + response.getBody().prettyPrint());
		}
	}
	
	public Response getApiResponse() {
		System.out.println("-------->" + this.response);
		return this.response;
	}
}

