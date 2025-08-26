package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDriverUserTest {
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userID,String userName, String fname, String lname, String email,String pwd, String ph) {
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response =UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) throws Throwable {
//		Response response=UserEndPoints.deleteUser(userName);
//		Assert.assertEquals(response.getStatusCode(), 200);
		
		 int maxRetries = 3;       // how many times we retry
		    int waitTime = 2000;      // wait time between retries (ms)
		    Response response = null;

		    for (int attempt = 1; attempt <= maxRetries; attempt++) {
		        response = UserEndPoints.deleteUser(userName);

		        if (response.getStatusCode() == 200 || response.getStatusCode() == 204) {
		            // Success → break early
		            break;
		        }

		        if (response.getStatusCode() == 404 && attempt < maxRetries) {
		            System.out.println("User not found, retrying in " + waitTime + " ms (attempt " + attempt + ")");
		            Thread.sleep(waitTime);
		        }
		    }

		    // Final assertion (after retries)
		    Assert.assertTrue(
		        response.getStatusCode() == 200 || response.getStatusCode() == 204,
		        "User deletion failed after retries. Last status: " + response.getStatusCode()
		    );
	}
}
