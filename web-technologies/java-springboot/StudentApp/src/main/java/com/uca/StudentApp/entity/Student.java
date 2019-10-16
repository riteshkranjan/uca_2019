package com.uca.StudentApp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private Float gpa;
	@Column(nullable = false)
	private Date insertedDate;

}
