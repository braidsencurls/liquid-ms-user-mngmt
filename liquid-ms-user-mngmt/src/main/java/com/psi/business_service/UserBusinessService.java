package com.psi.business_service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psi.constants.ResultConstants;
import com.psi.data_service.UserDataService;
import com.psi.models.ADPrincipalDetails;
import com.psi.models.AuthenticationRequest;
import com.psi.models.ResponsePayloadModel;
import com.psi.models.User;
import com.psi.parser.JsonParser;

@Service
public class UserBusinessService {
	
	@Autowired
	UserManagementValidation validation;
	
	@Autowired
	UserDataService userDataService;
	
	@Autowired
	JsonParser jsonParser;
	
	public ResponsePayloadModel<User> registerUser(String jsonString) {
		ResponsePayloadModel<User> response = new ResponsePayloadModel<User>();
		List<String> errors = response.getErrors().get("generalErrors");
		try {
			User user = (User) jsonParser.deSerialize(jsonString, new User());
			if(validation.isCreateUserValidParameters(user)) {
				userDataService.createUser(user);
				response.setData(user);
				response.setResult(ResultConstants.SUCCESS_CODE + " - " + ResultConstants.SUCCESS_DESCRIPTION);
			} else {
				errors.add(ResultConstants.INVALID_PARAMETER_CODE + " - " + ResultConstants.INVALID_PARAMETER_DECRIPTION);
				response.setErrors(response.getErrors());
			}
		} catch (PersistenceException e) {
			errors.add(ResultConstants.PERSISTENCE_ERROR_CODE + " - " + ResultConstants.PERSISTENCE_ERROR_DESCRIPTION);
			response.setErrors(response.getErrors());
		} catch (Exception e) {
			errors.add(ResultConstants.GENERIC_ERROR_CODE + " - " + ResultConstants.GENERIC_ERROR_DESC);
			response.setErrors(response.getErrors());
		}
		return response;
	}
	
	public ResponsePayloadModel<User> searchUser(String username) {
		ResponsePayloadModel<User> response = new ResponsePayloadModel<User>();
		List<String> errors = response.getErrors().get("generalErrors");
		try {
			User user = userDataService.searchUserByUsername(username);
			if(user != null) {
				response.setData(user);
				response.setResult(ResultConstants.SUCCESS_CODE + " - " + ResultConstants.SUCCESS_DESCRIPTION);
			} else {
				errors.add(ResultConstants.NO_DATA_FOUD_CODE + " - " + ResultConstants.NO_DATA_FOUND_DESC);
			}
		} catch(Exception e) {
			e.printStackTrace();
			errors.add(ResultConstants.GENERIC_ERROR_CODE + " - " + ResultConstants.GENERIC_ERROR_DESC);
			response.setErrors(response.getErrors());
		}
		
		return response;
	}
	
	public ResponsePayloadModel<List<User>> fetchAllUsers() {
		ResponsePayloadModel<List<User>> response = new ResponsePayloadModel<List<User>>();
		List<String> errors = response.getErrors().get("generalErrors");
		try {
			List<User> users = userDataService.fetchAllUsers();
			if(!users.isEmpty()) {
				response.setData(users);
				response.setResult(ResultConstants.SUCCESS_CODE + " - " + ResultConstants.SUCCESS_DESCRIPTION);
			} else {
				errors.add(ResultConstants.NO_DATA_FOUD_CODE + " - " + ResultConstants.NO_DATA_FOUND_DESC);
			}
		} catch(Exception e) {
			errors.add(ResultConstants.GENERIC_ERROR_CODE + " - " + ResultConstants.GENERIC_ERROR_DESC);
			response.setErrors(response.getErrors());
		}
		return response;
	}
	
	public ResponsePayloadModel<User> updateUser(String jsonString) {
		ResponsePayloadModel<User> response = new ResponsePayloadModel<User>();
		List<String> errors = response.getErrors().get("generalErrors");
		try {
			User user = (User) jsonParser.deSerialize(jsonString, new User());
			userDataService.updateUser(user);
			response.setResult(ResultConstants.SUCCESS_CODE + " - " + ResultConstants.SUCCESS_DESCRIPTION);
		} catch (Exception e) {
			errors.add(ResultConstants.GENERIC_ERROR_CODE + " - " + ResultConstants.GENERIC_ERROR_DESC);
			response.setErrors(response.getErrors());
		}
		return response;
	}
	
	public ResponsePayloadModel<User> deleteUser(String jsonString) {
		ResponsePayloadModel<User> response = new ResponsePayloadModel<User>();
		List<String> errors = response.getErrors().get("generalErrors");
		try {
			User user = (User) jsonParser.deSerialize(jsonString, new User());
			userDataService.removeUser(user);
			response.setResult(ResultConstants.SUCCESS_CODE + " - " + ResultConstants.SUCCESS_DESCRIPTION);
		} catch(Exception e) {
			errors.add(ResultConstants.GENERIC_ERROR_CODE + " - " + ResultConstants.GENERIC_ERROR_DESC);
			response.setErrors(response.getErrors());
		}
		return response;
	}
	
	public ResponsePayloadModel<User> authenticateUser(String jsonString) {
		ResponsePayloadModel<User> response = new ResponsePayloadModel<User>();
		List<String> errors = response.getErrors().get("generalErrors");
		try {
			AuthenticationRequest request = (AuthenticationRequest) jsonParser.deSerialize(jsonString, new AuthenticationRequest());
			if(validation.isAuthenticationValidParameters(request)) {
				boolean flag = userDataService.authenticate(request);
				if(flag) {
					response.setResult(ResultConstants.SUCCESS_CODE + " - " + ResultConstants.SUCCESS_DESCRIPTION);
				} else {
					errors.add(ResultConstants.BAD_CREDENTIAL_CODE + " - " + ResultConstants.BAD_CREDENTIAL_DECSC);
					response.setErrors(response.getErrors());
				}
			}
			
		} catch(Exception e) {
			errors.add(ResultConstants.GENERIC_ERROR_CODE + " - " + ResultConstants.GENERIC_ERROR_DESC);
			response.setErrors(response.getErrors());
		}
		return response;
	}
	
	public ResponsePayloadModel<ADPrincipalDetails> searchFromAD(String jsonString) {
		ResponsePayloadModel<ADPrincipalDetails> response = new ResponsePayloadModel<ADPrincipalDetails>();
		List<String> errors = response.getErrors().get("generalErrors");
		try {
			AuthenticationRequest request = (AuthenticationRequest) jsonParser.deSerialize(jsonString, new AuthenticationRequest());
			ADPrincipalDetails details = userDataService.searchFromAD(request);
			if(details != null) {
				response.setData(details);
				response.setResult(ResultConstants.SUCCESS_CODE + " - " + ResultConstants.SUCCESS_DESCRIPTION);
			} else {
				errors.add(ResultConstants.NO_DATA_FOUD_CODE + " - " + ResultConstants.NO_DATA_FOUND_DESC);
				response.setErrors(response.getErrors());
			}
		} catch(Exception e) {
			errors.add(ResultConstants.GENERIC_ERROR_CODE + " - " + ResultConstants.GENERIC_ERROR_DESC);
			response.setErrors(response.getErrors());
		}
		return response;
	}

}
