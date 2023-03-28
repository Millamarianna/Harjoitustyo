package Harjoitustyo.Yrityspeli.domain;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class SignupForm {
	
    @NotEmpty(message = "Please type in your username")
    @Size(min=4, max=30, message = "Username must be between 4 and 30 characters")
    private String username = "";
    
    @Email
    private String email = "";

    @NotEmpty(message = "Please type in password")
    @Size(min=7, max=30, message = "Password must be at least 7 characters long")
    private String password = "";

    @NotEmpty(message = "Please re-type your password")
    @Size(min=7, max=30, message = "Password must be at least 7 characters long")
    private String passwordCheck = "";

    @NotEmpty
    private String role = "USER";
    
    public SignupForm() {
		super();
	}

	public SignupForm(@NotEmpty @Size(min = 5, max = 30) String username, @Email String email,
			@NotEmpty @Size(min = 7, max = 30) String password, @NotEmpty @Size(min = 7, max = 30) String passwordCheck,
			@NotEmpty String role) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.role = role;
	}

	public SignupForm(@NotEmpty @Size(min = 5, max = 30) String username, @Email String email,
			@NotEmpty @Size(min = 7, max = 30) String password,
			@NotEmpty @Size(min = 7, max = 30) String passwordCheck) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordCheck = passwordCheck;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "SignupForm [username=" + username + ", email=" + email + ", password=" + password + ", passwordCheck="
				+ passwordCheck + ", role=" + role + "]";
	}
    
	
    
}
