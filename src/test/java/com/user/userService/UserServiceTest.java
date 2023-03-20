package com.user.userService;

import com.leaning.userApp.dao.UserDao;
import com.leaning.userApp.dao.entity.User;
import com.leaning.userApp.exception.DaoException;
import com.leaning.userApp.exception.DuplicateResourceFoundException;
import com.leaning.userApp.exception.ServiceException;
import com.leaning.userApp.service.UserService;
import com.leaning.userApp.service.UserServiceImpl;
import com.leaning.userApp.spec.UserRequestBo;
import com.leaning.userApp.spec.UserResponseBo;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.leaning.userApp.service.UserServiceImpl.dobValidator;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * @author rajatha.kunj
 */

public class UserServiceTest extends UserTestData {

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Mock
    private UserDao userDao;

    @Spy
    private DozerBeanMapper dozerMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByPersonalNumber_Success() throws ServiceException {
        String expectedPersonalNo = getUserBoList().get(0).getPersonalNumber();
        when(userDao.getUserByPersonalNumber(anyString())).thenReturn(Optional.ofNullable(getUserDataList().get(0)));
        UserResponseBo actual = userService.getUserByPersonalNumber(anyString());
        assertNotNull(actual);
        assertEquals(expectedPersonalNo, actual.getPersonalNumber());
    }


    @Test(expected = ServiceException.class)
    public void testGetUserByPersonalNumber_Exception() throws ServiceException {
        when(userDao.getUserByPersonalNumber(anyString())).thenThrow(new DaoException("Exception"));
        userService.getUserByPersonalNumber("ADMIN");
    }

    @Test(expected = ServiceException.class)
    public void testGetUserByPersonalNumber_Exception2() throws ServiceException {
        when(userDao.getUserByPersonalNumber(anyString())).thenReturn(Optional.ofNullable(null));
        userService.getUserByPersonalNumber("ADMIN");
    }


    @Test(expected = Exception.class)
    public void testCreateUser_duplicationServiceException() throws Exception {
        when(userDao.getUserByPersonalNumber(anyString())).thenThrow(new DuplicateResourceFoundException("Exception"));
        userService.saveUser(getUserBoList().get(0));
    }

    @Test(expected = ServiceException.class)
    public void testCreateUser_serviceException() throws Exception {
        UserRequestBo userBo = getUserBoList().get(0);
        userBo.setEmailAddress("email");
        userService.saveUser(userBo);
    }

    @Test
    public void testGetUserById_Success() throws ServiceException {
        String expectedUserId = getUserBoList().get(0).getUserId();
        when(userDao.getUserById(anyString())).thenReturn(Optional.ofNullable(getUserDataList().get(0)));
        UserResponseBo actual = userService.getUserById(anyString());
        assertNotNull(actual);
        assertEquals(expectedUserId, actual.getUserId());
    }


    @Test(expected = ServiceException.class)
    public void testGetUserByUserId_Exception() throws ServiceException {
        when(userDao.getUserById(anyString())).thenThrow(new DaoException("Exception"));
        userService.getUserById("ADMIN");
    }

    @Test(expected = ServiceException.class)
    public void testGetUserByUserId_Exception2() throws ServiceException {
        when(userDao.getUserById(anyString())).thenReturn(Optional.ofNullable(null));
        userService.getUserById("ADMIN");
    }


    @Test
    public void testSaveUser_Success() throws Exception {
        UserRequestBo userBo = getUserBoList().get(1);
        String expectedUserId = userBo.getUserId();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dob = format.parse("2022-11-13");
        userBo.setDateOfBirth(dob);
        when(userDao.getUserByPersonalNumber(anyString())).thenReturn(Optional.ofNullable(null));
        when(userDao.saveUser(any(User.class))).thenReturn(getUserDataList().get(1));

        UserResponseBo actual = userService.saveUser(userBo);
        assertNotNull(actual);
        assertEquals(expectedUserId, actual.getUserId());
    }

    @Test(expected = DuplicateResourceFoundException.class)
    public void testSaveUser_duplicateException() throws Exception {
        UserRequestBo userBo = getUserBoList().get(1);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dob = format.parse("2022-11-13");
        userBo.setDateOfBirth(dob);
        when(userDao.getUserByPersonalNumber(anyString())).thenReturn(Optional.ofNullable(getUserDataList().get(1)));
        userService.saveUser(userBo);
    }

    @Test(expected = ServiceException.class)
    public void testSaveUser_serviceException() throws Exception {
        UserRequestBo userBo = getUserBoList().get(1);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dob = format.parse("2022-11-14");
        userBo.setDateOfBirth(dob);
        dobValidator(userBo.getDateOfBirth(), userBo.getPersonalNumber());
        userService.saveUser(userBo);
    }


    @Test
    public void testGetUsers() throws Exception {
        String expectedUserId = getUserBoList().get(1).getUserId();
        when(userDao.getUsers()).thenReturn(getUserDataList());
        for (User user : getUserDataList()) {
            when(dozerMapper.map(user, UserRequestBo.class)).thenReturn(getUserBoList().get(1));
        }
        List<UserResponseBo> actual = userService.getUsers();
        userService.getUsers();
        assertEquals(expectedUserId, actual.get(1).getUserId());
    }

    @Test(expected = ServiceException.class)
    public void testGetUsers_exception() throws Exception {
        when(userDao.getUsers()).thenThrow(new DaoException("Exception"));
        userService.getUsers();
    }

    @Test
    public void testDeleteUserById_success() throws ServiceException {
       testGetUserById_Success();
        when(userDao.deleteUserById(anyString())).thenReturn(true);
        assertTrue(userService.deleteUserById(anyString()));
    }

    @Test(expected = ServiceException.class)
    public void testDeleteUserById_exception() throws ServiceException {
        testGetUserByUserId_Exception();
        userService.deleteUserById(anyString());
    }

    @Test(expected = ServiceException.class)
    public void testDeleteUserById_exception2() throws ServiceException {
        testGetUserById_Success();
        when(userDao.deleteUserById(anyString())).thenThrow(new DaoException());
        userService.deleteUserById(anyString());
    }

}

