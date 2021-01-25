package qumu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import api.builder.CreateUserLink;
import api.builder.GetUserLink;
import api.builder.GetUserListLink;
import api.builder.LoginUserLink;
import api.pojo.GetUserListResponseDTO;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class StepDefinition {
	int numOfPages;
	int userCount;
	List<GetUserListResponseDTO.Datum> userData = new ArrayList<GetUserListResponseDTO.Datum>();
	Set<String> userSetData = new HashSet<String>();
	GetUserListLink getUserListLink;
	GetUserLink getUserLink;
	CreateUserLink createUserLink;
	LoginUserLink loginUserLink;

	public void getListOfUsers(String pageNum) throws Exception {
		getUserListLink = new GetUserListLink("");
		if(pageNum != "") {
			getUserListLink.updateRequest("page", pageNum);
		}
		getUserListLink.executeAssertSuccess();
	}

	@Given("^I get the default list of users for on 1st page$")
	public void iGetTheDefaultListofusers() throws Exception {
		getListOfUsers("");
		numOfPages = getUserListLink.getResponse().getTotalPages();
		userCount = getUserListLink.getResponse().getTotal();
	}

	@When("I get the list of all users within every page")
	public void iGetTheListOfAllUsers() throws Exception {
		for (int i = 1; i <= numOfPages; i++) {
			getListOfUsers(String.valueOf(i));
			userData.addAll(getUserListLink.getResponse().getData());
		}
	}

	@Then("I should see total users count equals the number of user ids")
	public void iShouldMatchTotalCount() {
		Assert.assertEquals(this.userCount, userData.size());
	}

	@Given("I make a search for user (.*)")
	public void iMakeASearchForUser(String sUserID) throws Exception {
		getUserLink = new GetUserLink("");
		getUserLink.updateRequest("id", sUserID);
		getUserLink.createResponse();
	}

	@Then("I should see the following user data")
	public void IShouldSeeFollowingUserData(DataTable dt) {
	    List<Map<String, String>> rows = dt.asMaps(String.class, String.class);
	    Assert.assertEquals(rows.get(0).get("first_name"), getUserLink.getResponse().getData().getFirstName());
	    Assert.assertEquals(rows.get(0).get("email"), getUserLink.getResponse().getData().getEmail());
	}

	@Then("I receive error code (.*) in response")
	public void iReceiveErrorCodeInResponse(int responseCode) {
		Assert.assertEquals(responseCode, getUserLink.getApiResponse().getStatusCode());
	}

	@Given("I create a user with following (.*) (.*)")
	public void iCreateUserWithFollowing(String sUsername, String sJob) throws IOException {
		createUserLink = new CreateUserLink(CreateUserLink.sampleRequest);
		createUserLink.updateRequest(sUsername, sJob);
		createUserLink.createResponse();
	}

	@Then("response should contain the following data")
	public void iReceiveErrorCodeInResponse(DataTable dt) {
		List<List<String>> rows = dt.asLists(String.class);
		JsonPath path = createUserLink.getApiResponse().jsonPath();
		for(String str: rows.get(0)) {
			path.getString(str);
		}
	}

	@Given("I login unsuccessfully with the following data")
	public void iLoginSuccesfullyWithFollowingData(DataTable dt) throws Exception {
		List<Map<String, String>> rows = dt.asMaps(String.class, String.class);
	    loginUserLink = new LoginUserLink(LoginUserLink.sampleRequest);
	    loginUserLink.updateRequest(rows.get(0).get("Email"), rows.get(0).get("Password"));
	    loginUserLink.createResponse();
	}

	@Given("^I wait for the user list to load$")
	public void iWaitForUserListToLoad() throws Exception {
		getUserListLink = new GetUserListLink("");
		getUserListLink.updateRequest("delay", "3");
		getUserListLink.createResponse();
	}

	@Then("I should see that every user has a unique id")
	public void iShouldSeeThatEveryUserHasAUniqueID() {
		userData = getUserListLink.getResponse().getData();
		for(int i = 0; i < userData.size(); i++) {
			userSetData.add(userData.get(i).getId().toString());
		}
		Assert.assertEquals(userData.size(), userSetData.size());
	}

	@Then("^I should get a response code of (\\d+)$")
	public void iShouldGetAResponseCodeOf(int responseCode) {
		Assert.assertEquals(loginUserLink.getApiResponse().getStatusCode(), responseCode);
	}

	@And("^I should see the following response message:$")
	public void iShouldSeeTheFollowingResponseMessage(DataTable dt) {
		List<String> list = Arrays.asList(dt.asList(String.class).get(0).split(":"));
		System.out.println(list.get(1).trim());
		System.out.println(loginUserLink.getResponse().getError());
		Assert.assertTrue(list.get(1).trim().contains(loginUserLink.getResponse().getError()));
	}
}
