package com.maksdave.angularspringbootdemo.payload.request;

import com.maksdave.angularspringbootdemo.annotations.PasswordMatches;
import com.maksdave.angularspringbootdemo.annotations.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {
    @Email(message = "Email shall be in email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    @NotEmpty(message = "Please enter your name")
    private String firstname;
    @NotEmpty(message = "Please enter your lastname")
    private String lastname;
    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Please enter your password")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
    
    
    
}
