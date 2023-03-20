package com.leaning.userApp.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.leaning.userApp.dao.entity.User;
import com.leaning.userApp.dao.repository.UserRepository;
import com.leaning.userApp.dao.specification.UserSpecification;
import com.leaning.userApp.exception.DaoException;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @author rajatha.kunj
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() throws DaoException {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }


    @Override
    public User saveUser(User user) throws DaoException {
        try {
            user.setCreatedDate(Calendar.getInstance().getTime());
            return userRepository.save(user);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> getUserById(String userId) throws DaoException {
        try {
            return userRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> getUserByPersonalNumber(String personalNumber) throws DaoException {
        try {
            return userRepository.findByPersonalNumber(personalNumber);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteUserById(String userId) throws DaoException {
        try {
             userRepository.deleteByUserId(userId);
             return true;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Page<User> findAllPaginatedUserDetails(String searchTerm, String sortBy, String sortOrder, int page, int size) throws DaoException {
        try {
            PageRequest request = PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy);
            return userRepository.findAll(UserSpecification.textInAllColumns(searchTerm), request);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

}
