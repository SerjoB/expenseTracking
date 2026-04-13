package dev.expensetracking.users.controllers;

import dev.expensetracking.users.dto.ChangePasswordRequestDto;
import dev.expensetracking.users.dto.CreateUserRequestDto;
import dev.expensetracking.users.dto.CreateUserResponseDto;
import dev.expensetracking.users.dto.UserDto;
import dev.expensetracking.users.services.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public @ResponseBody CreateUserResponseDto createUser (@RequestBody @Valid CreateUserRequestDto body){
        return this.userService.createUser(body);
    }

    @PutMapping("/update/user")
    public @ResponseBody UserDto updateUser (@RequestBody @Valid UserDto body){
        return this.userService.updateUser(body);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable Long id){
        this.userService.deleteUser(id);
    }

    @PostMapping("/update/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword (@RequestBody @Valid ChangePasswordRequestDto body){
        this.userService.changePassword(body);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser (@PathVariable Long id){
        Optional<UserDto> user = this.userService.getUser(id);
        return ResponseEntity.of(user);
    }

}
