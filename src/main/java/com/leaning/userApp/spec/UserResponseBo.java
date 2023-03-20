package com.leaning.userApp.spec;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author rajatha.kunj
 */

@Setter
@Getter
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseBo implements Serializable {

    private static final long serialVersionUID = -6847896748328125245L;

    @ApiModelProperty(value = "User identifier", position = 1, required = true)
    @NotBlank(message = "User identifier cannot be null or empty.")
    @Size(min = 1, max = 45, message = "User identifier length must be between 1 to 45 characters")
    private String userId;

    @ApiModelProperty(value = "First name",required = true, position = 2)
    @Size(min = 0, max = 80, message = "First Name length must be between 0 to 80 characters")
    private String firstName;

    @ApiModelProperty(value = "Last name", position = 3)
    @Size(min = 0, max = 80, message = "Last Name length must be between 0 to 80 characters")
    private String lastName;

    @ApiModelProperty(value = "Date of birth", position = 4, required = true, notes = "yyyy-MM-dd", example = "2020-01-27")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @ApiModelProperty(value = "Personal Number", position = 5, required = true)
    @NotBlank(message = "User Personal cannot be null or empty.")
    @Size(min = 1, max = 45, message = "User identifier length must be between 1 to 45 characters")
    @Pattern(regexp = "yyyyMMDD-XXXX", message = "Invalid personal number")
    private String personalNumber;

    @ApiModelProperty(value = "Gender", position = 7, required = false, example = "M / F / U")
    @Size(max = 1, message = "Gender length must be 1 characters")
    private String gender;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
