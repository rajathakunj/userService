package com.user.userService;

import com.leaning.userApp.dao.entity.User;
import com.leaning.userApp.spec.UserRequestBo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author rajatha.kunj
 */
public class UserTestData {

    protected List<User> getUserDataList() {
        User user1 = new User();

        user1.setUserId("ADMIN");
        user1.setFirstName("Rajatha");
        user1.setLastName("Kunj");
        user1.setEmailAddress("user@email");
        user1.setCreatedDate(Calendar.getInstance().getTime());
        user1.setGender("M");
        user1.setDateOfBirth(new Date(2022-11-13));
        user1.setPersonalNumber("20221113-1234");

        User user2 = new User();
        user2.setUserId("Test");
        user2.setFirstName("Sumanth");
        user2.setLastName("Kulkarni");
        user2.setEmailAddress("user@email");
        user2.setCreatedDate(Calendar.getInstance().getTime());
        user2.setGender("M");
        user2.setDateOfBirth(new Date(2022-11-13));
        user2.setPersonalNumber("20221113-1234");
        return Arrays.asList(user1, user2);
    }
    protected List<UserRequestBo> getUserBoList() {
        UserRequestBo user1 = new UserRequestBo();

        user1.setUserId("ADMIN");
        user1.setFirstName("Rajatha");
        user1.setLastName("Kunj");
        user1.setEmailAddress("user@email");
        user1.setPhoneNumber("9880733577");
        user1.setGender("M");
        user1.setDateOfBirth(new Date(2022-11-13));
        user1.setPersonalNumber("20221113-1234");

        UserRequestBo user2 = new UserRequestBo();
        user2.setUserId("Test");
        user2.setFirstName("Sumanth");
        user2.setLastName("Kulkarni");
        user2.setEmailAddress("user@email");
        user2.setPhoneNumber("9880633577");
        user2.setGender("M");
        user2.setDateOfBirth(new Date(2022-11-13));
        user2.setPersonalNumber("20221113-1235");
        return Arrays.asList(user1, user2);
    }

}
