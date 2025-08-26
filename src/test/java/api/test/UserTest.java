package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	public Logger logger;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
//		userPayload.setUsername("shubham123");

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());

	}
	
	@Test(priority = 1)
	public void testPostUser() throws Exception {
		
		logger.info("********* Creating User ************");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********* User is Created ************");

	}
	
	@Test(priority = 2)
	public void testGetUserByName() throws Exception {

		logger.info("********* Reading User Info ************");

//		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		 Response response = null;
		    int retries = 5;          // number of attempts
		    int waitTime = 2000;      // wait 2 seconds between attempts

		    for (int i = 0; i < retries; i++) {
		        response = UserEndPoints.readUser(this.userPayload.getUsername());

		        if (response.getStatusCode() == 200) {
		            break;           // user found, stop retrying
		        }

		        Thread.sleep(waitTime); // wait before next attempt
		    }

		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

        Assert.assertEquals(response.jsonPath().getString("username"), this.userPayload.getUsername());
        Assert.assertEquals(response.jsonPath().getString("firstName"), this.userPayload.getFirstName());
        Assert.assertEquals(response.jsonPath().getString("lastName"), this.userPayload.getLastName());
        Assert.assertEquals(response.jsonPath().getString("email"), this.userPayload.getEmail());
        Assert.assertEquals(response.jsonPath().getString("phone"), this.userPayload.getPhone());
		logger.info("********* User Info Displayed ************");

	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("********* Updating User Info ************");

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response =UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("********* User Info Updated ************");

		
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() throws Exception {
		logger.info("********* Deleting User ************");

//		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		 Response response = null;
		    int retries = 5;
		    int waitTime = 2000;

		    for (int i = 0; i < retries; i++) {
		        response = UserEndPoints.deleteUser(this.userPayload.getUsername());

		        if (response.getStatusCode() == 200) {
		            break; // deleted successfully
		        }

		        System.out.println("DELETE failed, retrying... attempt " + (i + 1));
		        Thread.sleep(waitTime);
		    }
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********* User Deleted ************");

	}
	
	
	
}
