package dev.expensetracking.authentication.services;

import dev.expensetracking.authentication.dto.LoginRequestDto;
import dev.expensetracking.authentication.dto.LoginResponseDto;
import dev.expensetracking.authentication.models.AuthenticatedUser;

import java.util.Optional;

public interface AuthService {

    LoginResponseDto login (LoginRequestDto request);

    Optional<AuthenticatedUser> authorize (String credentials);

    void logout (AuthenticatedUser principal);

}
