package com.leaning.userApp.spec;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

/**
 * @author rajatha.kunj
 */
@Setter
@Getter
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBo extends UserResponseBo{

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Phone Number", position = 6, required = true)
    @Size(min = 1, max = 80, message = "User phone number length must be between 1 to 80 characters")
    private String phoneNumber;

    @ApiModelProperty(value = "User email", position = 8, required = true)
    @Size(min = 0, max = 80, message = "User email address length must be between 0 to 80 characters")
    @NotBlank(message = "Email Address cannot be null or empty.")
    private String emailAddress;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
    
    

}
