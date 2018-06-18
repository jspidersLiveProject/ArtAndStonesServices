package com.artandstones;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.artandstones.dao.MarbelDetailsRepository;
import com.artandstones.dao.UsersRepository;
import com.artandstones.model.MarbelDetails;
import com.artandstones.model.Users;
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
@EnableJpaRepositories("com.artandstones.dao")
@EntityScan("com.artandstones.model")
public class ArtAndStonesBackend {
	
	@Autowired
	MarbelDetailsRepository marbelDetailsRepository;
	
	@Autowired
	UsersRepository usersRepository;

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
		Optional<Users> user = usersRepository.findById(loginRequest.getCommonRequest().getUserName());
		System.out.println(user);
		if (loginRequest.getCommonRequest().getPassWord().equals(user.get().getPassword())) {
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
		List<MarbelDetails> marbelDetails= (List<MarbelDetails>) marbelDetailsRepository.findAll();
		System.out.println(marbelDetails);
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
