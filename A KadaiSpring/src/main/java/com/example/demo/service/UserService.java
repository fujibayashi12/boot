package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.Student;
import com.example.demo.repository.UserRepository;

/**
 * 生徒情報 service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
	
	/**
	 * 生徒情報 Repository
	 */
	@Autowired
	private UserRepository repo;
	
	/**
	 * 生徒情報 全検索
	 * @return 検索結果
	 */
	public List<Student> searchAll(){
		return repo.findAll();
	}
	
	/**
	 * 生徒情報 主キー検索
	 * @return 検索結果
	 */
	public Student findById(Long id) {
		return repo.findById(id).get();
	}
	
	/**
	 * 生徒情報　新規登録
	 * @param student 生徒情報
	 */
	public void create(UserRequest req) {
		Student student = new Student();
		student.setName(req.getName());
		student.setAddress(req.getAddress());
		student.setPhone(req.getPhone());
		repo.save(student);
	}
	
	/**
	 * 生徒情報更新
	 * @param student 生徒情報
	 */
	public void update(UserUpdateRequest upreq) {
		Student student = findById(upreq.getId());
		student.setAddress(upreq.getAddress());
		student.setName(upreq.getName());
		student.setPhone(upreq.getPhone());
		repo.save(student);
	}
}