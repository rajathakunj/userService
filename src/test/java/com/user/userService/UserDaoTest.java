package com.user.userService;

import com.leaning.userApp.dao.UserDao;
import com.leaning.userApp.dao.UserDaoImpl;
import com.leaning.userApp.dao.entity.User;
import com.leaning.userApp.exception.DaoException;
import com.leaning.userApp.dao.repository.UserRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author rajatha.kunj
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDaoTest extends UserTestData {

    @InjectMocks
    private final UserDao userDao = new UserDaoImpl();

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    User user = getUserDataList().get(1);

    @Test
    public void testGetUserByPersonalNumber_Success() throws DaoException {
        when(userRepository.findByPersonalNumber("20221113-1234")).thenReturn(Optional.ofNullable(getUserDataList().get(1)));
        Optional<User> user = userDao.getUserByPersonalNumber("20221113-1234");
        Assert.assertEquals("20221113-1234", user.get().getPersonalNumber());
    }

    @Test
    public void testGetUserByPersonalNumber_notNull() throws DaoException {
        when(userRepository.findByPersonalNumber("20221113-1234")).thenReturn(Optional.ofNullable(null));
        Optional<User> user = userDao.getUserByPersonalNumber("20221113-1234");
        assertNotNull(user);
    }

    @Test(expected = DaoException.class)
    public void testGetUserByPersonalNumber_exception() throws DaoException {
        when(userRepository.findByPersonalNumber("20221113-1234")).thenThrow(new DaoException("Exception"));
        userDao.getUserByPersonalNumber("20221113-1234");
    }


    @Test
    public void testSaveUser_success() throws DaoException {
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userDao.saveUser(user);
        Assert.assertEquals(user.getUserId(), savedUser.getUserId());
    }

    @Test(expected = DaoException.class)
    public void testSaveUser_exception() throws DaoException {
        when(userRepository.save(user)).thenThrow(new DaoException("Exception"));
         userDao.saveUser(user);
    }

    @Test
    public void testGetUserByUserId_Success() throws DaoException {
        when(userRepository.findByUserId("Test")).thenReturn(Optional.ofNullable(getUserDataList().get(1)));
        Optional<User> user = userDao.getUserById("Test");
        Assert.assertEquals("Test", user.get().getUserId());
    }

    @Test
    public void testGetUserByUserId_notNull() throws DaoException {
        when(userRepository.findByUserId("Test")).thenReturn(Optional.ofNullable(null));
        Optional<User> user = userDao.getUserById("Test");
        assertNotNull(user);
    }

    @Test(expected = DaoException.class)
    public void testGetUserByUserId_exception() throws DaoException {
        when(userRepository.findByUserId("Test")).thenThrow(new DaoException("Exception"));
        userDao.getUserById("Test");
    }

    @Test
    public void testGetUsers_success() throws DaoException {
        when(userRepository.findAll()).thenReturn(getUserDataList());
        List<User> users = userDao.getUsers();
        Assert.assertEquals(user.getUserId(), users.get(1).getUserId());
    }

    @Test(expected = DaoException.class)
    public void testGetUsers_exception() throws DaoException {
        when(userRepository.findAll()).thenThrow(new DaoException("Exception"));
        userDao.getUsers();
    }

    @Test
    public void testDeleteUserById_success() throws DaoException {
        when(userRepository.deleteByUserId("Test")).thenReturn(0);
        userDao.deleteUserById("Test");
        Assert.assertTrue(userDao.deleteUserById("Test"));
    }

    @Test(expected = DaoException.class)
    public void testDeleteUserById_exception() throws DaoException {
        when(userRepository.deleteByUserId("Test")).thenThrow(new DaoException("Exception"));
        userDao.deleteUserById("Test");
    }


}

