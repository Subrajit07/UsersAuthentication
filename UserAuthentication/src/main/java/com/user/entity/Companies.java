package com.user.entity;

import java.util.Set;

import com.user.enums.CompanySize;
import com.user.enums.WorkingDays;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "companies")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Companies extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "company_size")
	private CompanySize companySize;
	
	@Column(name = "registration_no")
	private String registrationNo;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = WorkingDays.class, fetch = FetchType.EAGER)
	@Column(name = "working_days")
	@CollectionTable(joinColumns = @JoinColumn(name = "company_id"), name = "company_working_days")
	private Set<WorkingDays> workingDays;
}
