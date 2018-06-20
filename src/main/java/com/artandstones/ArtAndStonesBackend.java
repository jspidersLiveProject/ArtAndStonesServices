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
import com.artandstones.request.CreateMarbelRequest;
import com.artandstones.request.DeleteMarbelRequest;
import com.artandstones.request.GetMarbelDetailsRequest;
import com.artandstones.request.GetMarblePriceRequest;
import com.artandstones.request.LoginRequest;
import com.artandstones.request.LogoutRequest;
import com.artandstones.request.UpdateMarbelRequest;
import com.artandstones.response.CommonResponse;
import com.artandstones.response.CreateMarbelResponse;
import com.artandstones.response.DeleteMarbelResponse;
import com.artandstones.response.GetMarbelDetailsResponse;
import com.artandstones.response.GetMarblePriceResponse;
import com.artandstones.response.LoginResponse;
import com.artandstones.response.LogoutResponse;
import com.artandstones.response.UpdateMarbelResponse;
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
		String password = usersRepository.getPasswordByUsername(loginRequest.getCommonRequest().getUserName());
		if (loginRequest.getCommonRequest().getPassWord().equals(password)) {
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
	
	@RequestMapping(value = "/getMarblePrice", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public GetMarblePriceResponse getMarblePrice(@RequestBody GetMarblePriceRequest getMarblePriceRequest) {
		GetMarblePriceResponse getMarblePriceResponse = new GetMarblePriceResponse();
		if (getMarblePriceRequest.getCommonRequest().getUserName().equals("Admin")) {
			String password = usersRepository.getPasswordByUsername(getMarblePriceRequest.getCommonRequest().getUserName());
			if (getMarblePriceRequest.getCommonRequest().getPassWord().equals(password)) {
				String price = marbelDetailsRepository.getMarblePriceByType(getMarblePriceRequest.getType());
				if(price == null) {
					getMarblePriceResponse.setMessage("Marbel Type Not Found");
					getMarblePriceResponse.setCommonResponse(new CommonResponse("790", ResponseStatus.FAILED));
				} else {
					getMarblePriceResponse.setPrice(price);
					getMarblePriceResponse.setMessage("Price Found");
					getMarblePriceResponse.setCommonResponse(new CommonResponse("200", ResponseStatus.SUCCESS));
				}
			} else {
				getMarblePriceResponse.setMessage("Your Password is not correct");
				getMarblePriceResponse.setCommonResponse(new CommonResponse("710", ResponseStatus.FAILED));
			}
		} else {
			getMarblePriceResponse.setMessage("You Are not Authorized to Update A Marbel");
			getMarblePriceResponse.setCommonResponse(new CommonResponse("720", ResponseStatus.FAILED));
		}
		return getMarblePriceResponse;
	}
	
	
	
	@RequestMapping(value = "/updateMarbel", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public UpdateMarbelResponse updateMarbel(@RequestBody UpdateMarbelRequest updateMarbelRequest) {
		UpdateMarbelResponse updateMarbelResponse = new UpdateMarbelResponse();
		if (updateMarbelRequest.getCommonRequest().getUserName().equals("Admin")) {
			Optional<Users> user = usersRepository.findById(updateMarbelRequest.getCommonRequest().getUserName());
			if (updateMarbelRequest.getCommonRequest().getPassWord().equals(user.get().getPassword())) {
				Optional<MarbelDetails> marbelDetails = marbelDetailsRepository.findById(updateMarbelRequest.getMarbelName());
				if(marbelDetails.isPresent()) {
					marbelDetails.get().setMarbelPrice(updateMarbelRequest.getPrice());
					marbelDetailsRepository.save(marbelDetails.get());
					updateMarbelResponse.setMessage("Marbel SuccessFully Got update");
					updateMarbelResponse.setCommonResponse(new CommonResponse("200", ResponseStatus.SUCCESS));
				} else {
					updateMarbelResponse.setMessage("Marbel Does not Exist");
					updateMarbelResponse.setCommonResponse(new CommonResponse("740", ResponseStatus.FAILED));
				}
				
			} else {
				updateMarbelResponse.setMessage("Your Password is not correct");
				updateMarbelResponse.setCommonResponse(new CommonResponse("710", ResponseStatus.FAILED));
			}
		} else {
			updateMarbelResponse.setMessage("You Are not Authorized to Update A Marbel");
			updateMarbelResponse.setCommonResponse(new CommonResponse("720", ResponseStatus.FAILED));
		}
		return updateMarbelResponse;
	}
	
	@RequestMapping(value = "/deleteMarbel", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public DeleteMarbelResponse deleteMarbel(@RequestBody DeleteMarbelRequest deleteMarbelRequest) {
		DeleteMarbelResponse deleteMarbelResponse = new DeleteMarbelResponse();
		if (deleteMarbelRequest.getCommonRequest().getUserName().equals("Admin")) {
			Optional<Users> user = usersRepository.findById(deleteMarbelRequest.getCommonRequest().getUserName());
			if (deleteMarbelRequest.getCommonRequest().getPassWord().equals(user.get().getPassword())) {
				try {
					marbelDetailsRepository.deleteById(deleteMarbelRequest.getMarbelName());
					deleteMarbelResponse.setMessage("Marbel Successfully Deleted");
					deleteMarbelResponse.setCommonResponse(new CommonResponse("200", ResponseStatus.SUCCESS));
				} catch (Exception e) {
					e.printStackTrace();
					deleteMarbelResponse.setMessage("Error Deleting Marbel");
					deleteMarbelResponse.setCommonResponse(new CommonResponse("730", ResponseStatus.FAILED));
				}
				
			} else {
				deleteMarbelResponse.setMessage("Your Password is not correct");
				deleteMarbelResponse.setCommonResponse(new CommonResponse("710", ResponseStatus.FAILED));
			}
		} else {
			deleteMarbelResponse.setMessage("You Are not Authorized to Delete A Marbel");
			deleteMarbelResponse.setCommonResponse(new CommonResponse("720", ResponseStatus.FAILED));
		}

		return deleteMarbelResponse;
	}

	@RequestMapping(value = "/createMarbel", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public CreateMarbelResponse createMarbel(@RequestBody CreateMarbelRequest createMarbelRequest) {
		CreateMarbelResponse createMarbelResponse = new CreateMarbelResponse();
		if (createMarbelRequest.getCommonRequest().getUserName().equals("Admin")) {
			Optional<Users> user = usersRepository.findById(createMarbelRequest.getCommonRequest().getUserName());
			if (createMarbelRequest.getCommonRequest().getPassWord().equals(user.get().getPassword())) {
				MarbelDetails marbelDetails = new MarbelDetails();
				marbelDetails.setMarbleName(createMarbelRequest.getMarbelName());
				marbelDetails.setMarbelType(createMarbelRequest.getMarbelType());
				marbelDetails.setMarbelPrice(createMarbelRequest.getMarbelPrice());
				marbelDetails.setMarbelDesc(createMarbelRequest.getMarbelDesc());
				marbelDetailsRepository.save(marbelDetails);
				createMarbelResponse.setMessage("Marbel Successfully Created");
				createMarbelResponse.setCommonResponse(new CommonResponse("200", ResponseStatus.SUCCESS));
			} else {
				createMarbelResponse.setMessage("Your Password is not correct");
				createMarbelResponse.setCommonResponse(new CommonResponse("710", ResponseStatus.FAILED));
			}
		} else {
			createMarbelResponse.setMessage("You Are not Authorized to Create A Marbel");
			createMarbelResponse.setCommonResponse(new CommonResponse("720", ResponseStatus.FAILED));
		}

		return createMarbelResponse;
	}

	@RequestMapping(value = "/getMarbelDetails", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public GetMarbelDetailsResponse getMarbelDetails(@RequestBody GetMarbelDetailsRequest getMarbelDetailsRequest) {
		List<MarbelDetails> marbelDetails = (List<MarbelDetails>) marbelDetailsRepository.findAll();
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
