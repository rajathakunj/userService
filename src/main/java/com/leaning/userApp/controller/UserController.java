package com.leaning.userApp.controller;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaning.userApp.exception.ServiceException;
import com.leaning.userApp.exception.DuplicateResourceFoundException;
import com.leaning.userApp.service.UserService;
import com.leaning.userApp.spec.UserRequestBo;
import com.leaning.userApp.spec.UserResponseBo;

import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * @author rajatha.kunj
 */

@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "User")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiIgnore
    @ApiOperation(value = "Get all users", notes = "Get all users", responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users list", response = UserRequestBo.class, responseContainer = "List")})
    @GetMapping(value = "/all")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get user by id", notes = "Get user by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User for the given user id", response = UserRequestBo.class),
            @ApiResponse(code = 404, message = "User not found for the given userId")})
    @GetMapping(value = "/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> getUserById(@PathVariable String userId) {
        try {
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiIgnore
    @ApiOperation(value = "Get user by personal number", notes = "Get user by personal number")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User for the given personal number", response = UserRequestBo.class),
            @ApiResponse(code = 404, message = "User not found for the given personal number")})
    @GetMapping(value = "/personalNumber/{personalNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> getUserByPersonalNo(@PathVariable String personalNumber) {
        try {
            return new ResponseEntity<>(userService.getUserByPersonalNumber(personalNumber), HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Save the given user details", notes = "Save the given user details", response = UserRequestBo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Saved user details", response = UserRequestBo.class),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 409, message = "Duplicate entry."),
            @ApiResponse(code = 500, message = "Internal server error.")})
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping()
    @Validated(UserRequestBo.class)
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserRequestBo userRequestBo) {
        try {
            return new ResponseEntity<>(userService.saveUser(userRequestBo), HttpStatus.CREATED);
        } catch (Exception e) {
            if (e instanceof DuplicateResourceFoundException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            } else if (e instanceof ServiceException) {
                return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Update the user details for the specified user id", notes = "Update the user details for the specified user id", response = UserResponseBo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated user details", response = UserResponseBo.class),
            @ApiResponse(code = 400, message = "Bad Request. Error while processing request"),
            @ApiResponse(code = 404, message = "Invalid user "),
            @ApiResponse(code = 500, message = "Internal server error.")
    })
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(
            @ApiParam(name = "userId", value = "User id", required = true) @PathVariable String userId,
            @RequestBody @Valid UserRequestBo userRequestBo) {
        try {
            return new ResponseEntity<>(userService.updateUser(userId, userRequestBo), HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Delete the User for the specified User Id", notes = "Delete the User for the specified User Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted", response = Object.class),
            @ApiResponse(code = 404, message = "User with given id does not exist")})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Object> deleteUser(@ApiParam(name = "userId", value = "User Id", required = true) @PathVariable String userId) {
        try {
            return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get users by search and sort", notes = "Get users by search and sort", responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users list", response = UserRequestBo.class, responseContainer = "List")})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/users")
    public ResponseEntity<Object> getAllUserDetails(
            @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm,
            @ApiParam(value = "Sort by firstName", name = "sortBy", defaultValue = "firstName", allowableValues = "firstName,personalNumber") @RequestParam(name = "sortBy", defaultValue = "firstName") String sortBy,
            @ApiParam(name = "sortOrder", value = "Provide the sorting Order, Eg : ASC.", defaultValue = "ASC", allowableValues = "ASC,DESC") @RequestParam(name = "sortOrder", defaultValue = "ASC") @Valid @Pattern(regexp = "ASC|DESC") String sortOrder,
            @ApiParam(name = "page", value = "Enter Pages, Eg : 1", defaultValue = "0") @RequestParam(name = "page", defaultValue = "0") @Valid @Min(value = 0, message = "Please Enter a positive value.") Integer page,
            @ApiParam(name = "size", value = "Enter Size of each page, Eg : 5.", defaultValue = "10") @RequestParam(name = "size", defaultValue = "10") @Valid @Min(value = 1, message = "Please enter a value 1 or greater") Integer size) {
        try {
            return new ResponseEntity<>(userService.getPaginatedUserDetails(searchTerm, sortBy, sortOrder, page, size), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
