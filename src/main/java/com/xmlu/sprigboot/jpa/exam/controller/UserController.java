package com.xmlu.sprigboot.jpa.exam.controller;

import com.xmlu.sprigboot.jpa.exam.dao.model.User;
import com.xmlu.sprigboot.jpa.exam.dto.JsonResult;
import com.xmlu.sprigboot.jpa.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 根据ID查询用户
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserById(@PathVariable(value = "id") Integer id)
	{
		JsonResult r = new JsonResult();
		try {
			User user = userService.getUserById(id);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
		}
		return ResponseEntity.ok(r);
	}
}