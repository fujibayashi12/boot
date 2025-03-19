package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * リクエストデータ	
 */
@Data
public class UserRequest implements Serializable{
	/**
	 * 名前
	 */
	@NotEmpty(message = "名前を入力してください")
	@Size(max = 20, message = "名前は20文字以内にしてください")
	private String name;
	
	/**
	 * 住所
	 */
	@Size(max = 50, message = "住所は50文字以内にしてください")
	private String address;
	
	/**
	 * 電話番号
	 */
	@Pattern(regexp = "0\\d{1,4}-\\d{1,4}-\\d{4}", message = "電話番号の形式で入力してください")
	private String phone;
}
