package dev.expensetracking.accounts.services;

import dev.expensetracking.accounts.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountDto createAccount (AccountDto request);

    AccountDto updateAccount (AccountDto request);

    void deleteAccount (Long id);

    Optional<AccountDto> findAccountById(Long id);

    List<AccountDto> findAllAccountsForUser(Long userId);

}
