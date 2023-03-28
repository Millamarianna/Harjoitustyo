package Harjoitustyo.Yrityspeli.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message="Field cannot be empty")
	@Size(min = 1, max = 30, message="Name needs to be less than 30 characters")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	@JsonIgnore
	private List<Data> data;
		
	public Company() {
		super();
	}
	
	public Company(String name) {
		super();
		this.name = name;
	}
	
	public Company(String name, List<Data> data) {
		super();
		this.name = name;
		this.data= data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	
}
