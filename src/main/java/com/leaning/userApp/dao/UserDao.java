package com.leaning.userApp.dao;

import org.springframework.data.domain.Page;

import com.leaning.userApp.dao.entity.User;
import com.leaning.userApp.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    /**
     * @return
     * @throws DaoException
     */
    public List<User> getUsers() throws DaoException;

    /**
     * @param user
     * @return
     * @throws DaoException
     */
    public User saveUser(User user) throws DaoException;

    /**
     * @param userId
     * @return
     * @throws DaoException
     */
    public Optional<User> getUserById(String userId) throws DaoException;

    /**
     * @param personalNumber
     * @return
     * @throws DaoException
     */
    Optional<User> getUserByPersonalNumber(String personalNumber) throws DaoException;

    /**
     * @param userId
     * @return
     * @throws DaoException
     */
    public boolean deleteUserById(String userId) throws DaoException;

    /**
     * @param searchTerm
     * @param sortBy
     * @param sortOrder
     * @param page
     * @param size
     * @return
     * @throws DaoException
     */

    Page<User> findAllPaginatedUserDetails(String searchTerm, String sortBy, String sortOrder, int page, int size) throws DaoException;
}
