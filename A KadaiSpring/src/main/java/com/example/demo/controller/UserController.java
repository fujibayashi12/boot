package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.Student;
import com.example.demo.service.UserService;


/**
 * 生徒情報 Controller
 */
@Controller
public class UserController {

	
	/**
	 * 生徒情報 Service
	 */
	@Autowired
	private UserService service;
	
	
	/**
	 * 生徒情報一覧表示
	 * @param model Model
	 * @return 一覧用HTML
	 */
	@GetMapping(value = "/student/list")
	public String displayList(Model model) {
		List<Student> studentlist = service.searchAll();
		model.addAttribute("studentlist",studentlist);
		return "student/list";
	}
	
	
	/**
	 * 新規登録画面表示
	 * @param model Model
	 * @retuan 情報一覧画面
	 */
	@GetMapping(value = "/student/add")
	public String displayAdd(Model model) {
		model.addAttribute("req",new UserRequest());
		return "student/add";
	}
	
	/**
	 * 生徒新規登録
	 * @param req リクエストデータ
	 * @param model Model
	 * @return 一覧画面
	 */
	@RequestMapping(value = "/student/create", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute("req") UserRequest req, BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError  error : result.getAllErrors()){
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "student/add";
		}
		//生徒情報の登録
		service.create(req);
		return "redirect:/student/list";
	}
	
	/**
	 * 詳細画面を表示
	 * @param id 表示するID
	 * @param model Model
	 * @return 詳細画面
	 */
	@GetMapping("/student/{id}")
	public String displayView(@PathVariable Long id, Model model) {
		Student student = service.findById(id);
		model.addAttribute("Data",student);
		return "student/view";
	}
	
	/**
	 * 編集画面を表示
	 * @param id 表示するID
	 * @param model Model
	 * @return 生徒編集画面
	 */
	@GetMapping("/student/{id}/edit")
	public String displayEdit(@PathVariable Long id, Model model) {
		Student student = service.findById(id);
		UserUpdateRequest upreq = new UserUpdateRequest();
		upreq.setId(student.getId());
		upreq.setName(student.getName());
		upreq.setPhone(student.getPhone());
		upreq.setAddress(student.getAddress());
		model.addAttribute("upreq", upreq);
		return "student/edit";
	}
	
	@RequestMapping(value = "/student/update",method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute UserUpdateRequest upreq,BindingResult result, Model model) {
		
		if(result.hasErrors ()) {
			List<String> errorList = new ArrayList<String>();
			
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "student/edit";
		}
		
		//生徒情報更新
		service.update(upreq);
		return String.format("redirect:/student/%d", upreq.getId());
	}	
}
