package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Helper.UserRequest;

public interface UserService {

    /**
     * Creates a new user in the system.
     *
     * @param userRequest The user details to create.
     * @return The created UserDto.
     */
    public UserDto create(UserRequest userRequest);

    /**
     * Retrieves a list of all user entries in the system.
     *
     * @return A list of UserDto representing all users.
     */
    public List<UserDto> viewAll();

    /**
     * Retrieves a user entry by its ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The UserDto with the specified ID.
     */
    public UserDto viewById(int userId);

    /**
     * Deletes a user entry by email.
     *
     * @param email The email of the user to delete.
     */
    public void delete(String email);

    /**
     * Retrieves a user entry by email.
     *
     * @param email The email of the user to retrieve.
     * @return The UserDto with the specified email.
     */
    public UserDto viewByEmail(String email);

    /**
     * Updates a user entry in the system.
     *
     * @param userDto The updated user details.
     * @param userId  The ID of the user to update.
     * @return The updated UserDto.
     */
    public UserDto update(UserDto userDto, int userId);
}
