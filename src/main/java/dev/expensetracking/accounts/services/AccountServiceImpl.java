package dev.expensetracking.accounts.services;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import dev.expensetracking.accounts.dto.AccountDto;
import dev.expensetracking.accounts.dto.AccountDtoMapper;
import dev.expensetracking.accounts.exceptions.AccountNotFoundException;
import dev.expensetracking.accounts.models.Account;
import dev.expensetracking.accounts.repositories.AccountRepository;
import dev.expensetracking.users.models.User;
import dev.expensetracking.users.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final static SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
    private final static AccountDtoMapper mapper = new AccountDtoMapper();

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto request) {
        Long userId = Long.parseLong(request.getUserId());
        User user = this.userRepository.getReferenceById(userId);
        Account account = new Account.AccountBuilder()
                .withUser(user)
                .withId(snowflakeGenerator.next())
                .withName(request.getName())
                .withType(request.getType())
                .build();
        Account result  = this.accountRepository.save(account);
        return mapper.apply(result);
    }

    @Override
    public AccountDto updateAccount(AccountDto request) {
        Objects.requireNonNull(request.getId());
        Long id = Long.parseLong(request.getId());
        Account account = this.accountRepository
                .findById(id)
                .orElseThrow(AccountNotFoundException::new);

        account.setName(request.getName());
        account.setType(request.getType());

        Account result = this.accountRepository.save(account);
        return mapper.apply(result);
    }

    @Override
    public void deleteAccount(Long id) {
        this.accountRepository.deleteById(id);
    }

    @Override
    public Optional<AccountDto> findAccountById(Long id) {
        return this.accountRepository.findById(id).map(mapper);
    }

    @Override
    public List<AccountDto> findAllAccountsForUser(Long userId) {
        return this.accountRepository.findAllByUserId(userId)
                .stream().sorted().map(mapper).toList();
    }
}
