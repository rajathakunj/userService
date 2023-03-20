package com.leaning.userApp.service;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leaning.userApp.dao.UserDao;
import com.leaning.userApp.dao.entity.User;
import com.leaning.userApp.exception.DaoException;
import com.leaning.userApp.exception.DuplicateResourceFoundException;
import com.leaning.userApp.exception.ServiceException;
import com.leaning.userApp.spec.UserDetailsBo;
import com.leaning.userApp.spec.UserRequestBo;
import com.leaning.userApp.spec.UserResponseBo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author rajatha.kunj
 */

@Service
@Transactional(rollbackFor = {ServiceException.class})
public class UserServiceImpl implements UserService {

    protected DozerBeanMapper dozerMapper = new DozerBeanMapper();

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserResponseBo> getUsers() throws ServiceException{
        List<UserResponseBo> userBos = null;
        try {
            List<User> users = userDao.getUsers();
            if (CollectionUtils.isNotEmpty(users)) {
                userBos = users.stream().map(user -> dozerMapper.map(user, UserResponseBo.class)).collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return userBos;
    }

    @Override
    public UserResponseBo getUserByPersonalNumber(String personalNumber) throws ServiceException {
        UserResponseBo userBo = null;
        try {
            Optional<User> userOpt = userDao.getUserByPersonalNumber(personalNumber);
            if (!userOpt.isPresent()) {
                throw new ServiceException("User not found for the provided personal number " + personalNumber);
            }
            userBo = dozerMapper.map(userOpt.get(), UserResponseBo.class);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return userBo;
    }

    public static boolean emailMatches(String emailAddress) {
        String regexPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public static boolean dobValidator(Date dateOfBirth, String personalNumber) {
        boolean isValid= false;
        String separator = "-";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dob = dateFormat.format(dateOfBirth);
        dob = dob.replace(separator, "");
        String personalNo = personalNumber.substring(0, personalNumber.indexOf(separator));
        if (dob.equalsIgnoreCase(personalNo)) {
             isValid= true;
        }
        return isValid;
    }

    @Override
    public UserResponseBo saveUser(UserRequestBo userBo) throws ServiceException,DuplicateResourceFoundException {
        UserResponseBo createdUser = null;
        try {
            if (null != userBo) {
                if (null != userBo.getEmailAddress() && !emailMatches(userBo.getEmailAddress())) {
                    throw new ServiceException("Invalid email address");
                }
                if (null != userBo.getDateOfBirth() && null != userBo.getPersonalNumber() && !dobValidator(userBo.getDateOfBirth(), userBo.getPersonalNumber())) {
                    throw new ServiceException("Mismatch in Date of birth and Personal number " + userBo.getDateOfBirth() + " and " + userBo.getPersonalNumber());
                }
                Optional<User> userOpt = userDao.getUserByPersonalNumber(userBo.getPersonalNumber());
                if (userOpt.isPresent()) {
                    throw new DuplicateResourceFoundException("Duplicate entry for User personal number " + userBo.getPersonalNumber());
                }
                User user = userDao.saveUser(dozerMapper.map(userBo, User.class));
                createdUser = dozerMapper.map(user, UserResponseBo.class);
            }
        } catch (DuplicateResourceFoundException e) {
            throw new DuplicateResourceFoundException(e);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        return createdUser;
    }

    @Override
    public UserResponseBo updateUser(String userId, UserRequestBo userBo) throws ServiceException {
        UserResponseBo userResponseBo = null;
        try {
            if (null != userBo) {
                userBo.setUserId(userId);
                Optional<User> user = userDao.getUserById(userId);
                if (user.isPresent()) {
                    if (!user.get().getPersonalNumber().equalsIgnoreCase(userBo.getPersonalNumber()) || (user.get().getDateOfBirth().compareTo(userBo.getDateOfBirth())==0)) {
                        throw new ServiceException("User personal number or Date of birth can not be edited");
                    }
                    if (null != userBo.getEmailAddress() && !emailMatches(userBo.getEmailAddress())) {
                        throw new ServiceException("Invalid email address");
                    }
                    User updatedUser = userDao.saveUser(dozerMapper.map(userBo, User.class));
                    if (updatedUser != null) {
                        userResponseBo = dozerMapper.map(updatedUser, UserResponseBo.class);
                    }
                }
            }
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        return userResponseBo;
    }

    @Override
    public UserResponseBo getUserById(String userId) throws ServiceException {
        UserResponseBo userBo = null;
        try {
            Optional<User> userOpt = userDao.getUserById(userId);
            if (!userOpt.isPresent()) {
                throw new ServiceException("User not found for the provided userId :" +userId);
            }
            userBo = dozerMapper.map(userOpt.get(), UserResponseBo.class);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return userBo;
    }

    @Override
    public boolean deleteUserById(String userId) throws ServiceException {
        try {
            Optional<User> userOpt = userDao.getUserById(userId);
            if (!userOpt.isPresent()) {
                throw new ServiceException("User not found for the provided user id :"+userId);
            }
            return userDao.deleteUserById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public UserDetailsBo getPaginatedUserDetails(String searchTerm, String sortBy, String sortOrder, int page, int size) throws ServiceException {
        UserDetailsBo userDetailsBo = new UserDetailsBo();
        List<UserResponseBo> userResponseBos = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        try {
            Page<User> userPage = userDao.findAllPaginatedUserDetails(searchTerm, sortBy, sortOrder, page, size);
            userList = userPage.getContent();
            if (CollectionUtils.isNotEmpty(userList)) {
                userResponseBos = userList.stream().map(user -> dozerMapper.map(user, UserResponseBo.class)).collect(Collectors.toList());
            }
            userDetailsBo.setUsers(userResponseBos);
            userDetailsBo.setTotalRecordsCount(userPage.getTotalElements());
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return userDetailsBo;
    }
}
