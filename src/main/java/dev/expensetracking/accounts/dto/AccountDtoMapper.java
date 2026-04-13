package dev.expensetracking.accounts.dto;

import dev.expensetracking.accounts.models.Account;

import java.util.function.Function;

public final class AccountDtoMapper implements Function<Account, AccountDto> {

    @Override
    public AccountDto apply(Account account) {
        AccountDto result = new AccountDto();
        result.setId(account.getId().toString());
        result.setName(account.getName());
        result.setType(account.getType());
        result.setUserId(account.getUser().getId().toString());
        return result;
    }

}
