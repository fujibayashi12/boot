package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 生徒情報更新リクエストデータ	
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserUpdateRequest extends UserRequest implements Serializable {

	/**
	 * 生徒ID
	 */
	@NotNull
	private Long id;
}
