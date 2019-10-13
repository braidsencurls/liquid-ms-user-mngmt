package com.psi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psi.business_service.UserBusinessService;
import com.psi.models.ADPrincipalDetails;
import com.psi.models.ResponsePayloadModel;
import com.psi.models.User;

@RestController
@RequestMapping("/user-management")
public class UserMngmtController {
	
	private static Logger logger = LoggerFactory.getLogger(UserMngmtController.class);
	
	@Autowired
	UserBusinessService userService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test() {
		return "Success";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
	@ResponseBody
	public ResponsePayloadModel<User> createUser(@RequestBody String jsonString) {
		logger.info("Request Parameter: " + jsonString);
		return userService.registerUser(jsonString);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponsePayloadModel<User> searchUser(@RequestParam("username") String username) {
		logger.info("Searching for username: " + username);
		return userService.searchUser(username);
	}
	
	@RequestMapping(value = "/fetch-users", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponsePayloadModel<List<User>> fetchAllUsers() {
		logger.info("Fetching all users");
		return userService.fetchAllUsers();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponsePayloadModel<User> update(@RequestBody String jsonString) {
		return userService.updateUser(jsonString);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8", consumes = "application/json")
	@ResponseBody
	public ResponsePayloadModel<User> removeUser(@RequestBody String jsonString) {
		return userService.deleteUser(jsonString);
	}
	
	@RequestMapping(value = "/active-dir-auth", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
	@ResponseBody
	public ResponsePayloadModel<User> authenticate(@RequestBody String jsonString) {
		return userService.authenticateUser(jsonString);
	}
	
	@RequestMapping(value = "/active-dir-search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
	@ResponseBody
	public ResponsePayloadModel<ADPrincipalDetails> searchFromActiveDir(@RequestBody String jsonString) {
		return userService.searchFromAD(jsonString);
	}

}
