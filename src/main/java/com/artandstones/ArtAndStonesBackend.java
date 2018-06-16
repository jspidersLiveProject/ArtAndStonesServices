package com.artandstones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.artandstones.request.GetMarbelDetailsRequest;
import com.artandstones.request.LoginRequest;
import com.artandstones.request.LogoutRequest;
import com.artandstones.response.CommonResponse;
import com.artandstones.response.GetMarbelDetailsResponse;
import com.artandstones.response.LoginResponse;
import com.artandstones.response.LogoutResponse;
import com.artandstones.util.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping(path = { "aas/" })
@CrossOrigin
public class ArtAndStonesBackend {

	public static void main(String[] args) {
		SpringApplication.run(ArtAndStonesBackend.class, args);
	}

	@RequestMapping("/")
	public String hello() {

		return "Hello jspiders";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public LoginResponse ValidateUser(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = new LoginResponse();
		if (loginRequest.getCommonRequest().getUserName().equals("Admin")
				&& loginRequest.getCommonRequest().getPassWord().equals("Passowrd")) {
			loginResponse.setAuthenticated(true);
			loginResponse.setCommonResponse(new CommonResponse("200", ResponseStatus.SUCCESS));
		} else {
			loginResponse.setAuthenticated(false);
			loginResponse.setCommonResponse(new CommonResponse("700", ResponseStatus.FAILED));
		}
		return loginResponse;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public LogoutResponse logout(@RequestBody LogoutRequest logoutRequest) {
		LogoutResponse logoutResponse = new LogoutResponse();
		if (logoutRequest.getUserName().equals("Admin")) {
			logoutResponse.setMessage("Logout SuccessFully");
			logoutResponse.setCommonResponse(new CommonResponse("200", ResponseStatus.SUCCESS));
		} else {
			logoutResponse.setMessage("User is not Looged in");
			logoutResponse.setCommonResponse(new CommonResponse("701", ResponseStatus.FAILED));
		}

		return logoutResponse;
	}

	@RequestMapping(value = "/getMarbelDetails", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public GetMarbelDetailsResponse getMarbelDetails(@RequestBody GetMarbelDetailsRequest getMarbelDetailsRequest) {
		GetMarbelDetailsResponse getMarbelDetailsResponse = new GetMarbelDetailsResponse();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setCommonRequest(getMarbelDetailsRequest.getCommonRequest());
		LoginResponse loginResponse = ValidateUser(loginRequest);
		if (loginResponse.isAuthenticated()) {
			String marbelName = getMarbelDetailsRequest.getMarbelName();
			if (marbelName.equals("Marbel1")) {
				getMarbelDetailsResponse.setDescription("this is marbel 1");
				getMarbelDetailsResponse.setMarbelName(marbelName);
				getMarbelDetailsResponse.setPrice(1000);
				getMarbelDetailsResponse.setType("T1");
			}
			if (marbelName.equals("Marbel2")) {
				getMarbelDetailsResponse.setDescription("this is marbel 2");
				getMarbelDetailsResponse.setMarbelName(marbelName);
				getMarbelDetailsResponse.setPrice(2000);
				getMarbelDetailsResponse.setType("T2");
			}
			if (marbelName.equals("Marbel3")) {
				getMarbelDetailsResponse.setDescription("this is marbel 3");
				getMarbelDetailsResponse.setMarbelName(marbelName);
				getMarbelDetailsResponse.setPrice(3000);
				getMarbelDetailsResponse.setType("T3");
			}
			
			getMarbelDetailsResponse.setCommonResponse(new CommonResponse("200", ResponseStatus.SUCCESS));
		} else {
			getMarbelDetailsResponse.setCommonResponse(new CommonResponse("700", ResponseStatus.FAILED));
		}

		return getMarbelDetailsResponse;
	}

}
