package com.leaning.userApp.spec;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * @author rajatha.kunj
 */
@Setter
@Getter
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsBo {

    private static final long serialVersionUID = -4143647664353523341L;

    private List<UserResponseBo> users;

    private long totalRecordsCount;

	public List<UserResponseBo> getUsers() {
		return users;
	}

	public void setUsers(List<UserResponseBo> users) {
		this.users = users;
	}

	public long getTotalRecordsCount() {
		return totalRecordsCount;
	}

	public void setTotalRecordsCount(long totalRecordsCount) {
		this.totalRecordsCount = totalRecordsCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
