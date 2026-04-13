package dev.expensetracking.users.services;

import dev.expensetracking.users.dto.ChangePasswordRequestDto;
import dev.expensetracking.users.dto.CreateUserRequestDto;
import dev.expensetracking.users.dto.CreateUserResponseDto;
import dev.expensetracking.users.dto.UserDto;

import java.util.Optional;

public interface UserService {

    CreateUserResponseDto createUser (CreateUserRequestDto request);

    Optional<UserDto> getUser (Long userId);

    UserDto updateUser (UserDto request);

    void deleteUser (Long userId);

    void changePassword (ChangePasswordRequestDto request);

}
