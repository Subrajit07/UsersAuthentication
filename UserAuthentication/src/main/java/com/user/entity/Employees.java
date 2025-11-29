package com.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.user.enums.Department;
import com.user.enums.Gender;
import com.user.enums.MaritalStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "employees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employees extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(name = "full_name", nullable = false)
	private String fullName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender=Gender.Others;
	
	@Column(name = "date_of_birth")
	private LocalDate dob;
	
	@Column(name = "mobile_no", length = 10)
	private String mobileNo;
	
	private String address;
	
	private String nationality;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "marital_status")
	private MaritalStatus maritalStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "department")
	private Department department;
}
