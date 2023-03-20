package com.leaning.userApp.dao.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leaning.userApp.dao.entity.User;

import java.util.Optional;

/**
 * @author rajatha.kunj
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * @param userId
     * @return
     */
    Optional<User> findByUserId(String userId);

    /**
     * @param userId
     * @return
     */
    Integer deleteByUserId(String userId);

    /**
     * @param personalNumber
     * @return
     */
    Optional<User> findByPersonalNumber(String personalNumber);

    /**
     * @param spec
     * @param pageRequest
     * @return
     */
    Page<User> findAll(Specification<User> spec, Pageable pageRequest);
}