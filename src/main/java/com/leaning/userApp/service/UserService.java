package com.leaning.userApp.service;

import java.util.List;

import com.leaning.userApp.exception.DuplicateResourceFoundException;
import com.leaning.userApp.exception.ServiceException;
import com.leaning.userApp.spec.UserDetailsBo;
import com.leaning.userApp.spec.UserRequestBo;
import com.leaning.userApp.spec.UserResponseBo;

/**
 * @author rajatha.kunj
 */
public interface UserService {

    /**
     * @return
     * @throws ServiceException
     */
    List<UserResponseBo> getUsers() throws ServiceException;

    /**
     * @param personalNumber
     * @return
     * @throws ServiceException
     */
    UserResponseBo getUserByPersonalNumber(String personalNumber) throws ServiceException;

    /**
     * @param userBo
     * @return
     * @throws ServiceException
     * @throws DuplicateResourceFoundException
     */
    UserResponseBo saveUser(UserRequestBo userBo) throws ServiceException, DuplicateResourceFoundException;

    /**
     * @param userId
     * @param userBo
     * @return
     * @throws ServiceException
     */
    UserResponseBo updateUser(String userId, UserRequestBo userBo) throws ServiceException;

    /**
     * @param userId
     * @return
     * @throws ServiceException
     */
    UserResponseBo getUserById(String userId) throws ServiceException;

    /**
     * @param userId
     * @return
     * @throws ServiceException
     */
    boolean deleteUserById(String userId) throws ServiceException;

    /**
     * @param searchTerm
     * @param sortBy
     * @param sortOrder
     * @param page
     * @param size
     * @return
     * @throws ServiceException
     */
    UserDetailsBo getPaginatedUserDetails(String searchTerm, String sortBy, String sortOrder, int page, int size) throws ServiceException;
}
