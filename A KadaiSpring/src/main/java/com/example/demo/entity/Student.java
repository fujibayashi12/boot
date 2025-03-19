package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity 		//このアノテーションがついてるクラスにはテーブルと対応する。つまりこのクラスがテーブルの中身を管理する
@Data			//getter,setterを省略できる
@Table(name = "student")
public class Student implements Serializable {

		
		/**
		 * 出席番号
		 */
		@Id
		@Column(name = "id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		/**
		 * 名前
		 */
		@Column(name = "name")
		private String name;
		
		/**
		 * 住所
		 */
		@Column(name = "address")
		private String address;
		
		/**
		 * 電話番号
		 */
		@Column(name = "phone")
		private String phone;
		
	}

