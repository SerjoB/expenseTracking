package dev.expensetracking.currencies.services;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import dev.expensetracking.currencies.dto.CurrencyDto;
import dev.expensetracking.currencies.dto.CurrencyDtoMapper;
import dev.expensetracking.currencies.models.Currency;
import dev.expensetracking.currencies.repositories.CurrencyRepository;
import dev.expensetracking.users.events.NewUserCreatedEvent;
import dev.expensetracking.users.models.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final static SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<CurrencyDto> getCurrenciesForUser(Long userId) {
        CurrencyDtoMapper mapper = new CurrencyDtoMapper();
        return this.currencyRepository.findAllByUserId(userId)
                .stream()
                .map(mapper)
                .toList();
    }

    @EventListener(NewUserCreatedEvent.class)
    public void onNewUserCreatedEventListener (NewUserCreatedEvent event) {
        User user = event.getUser();
        // Create currency
        Currency currency = new Currency();
        currency.setUser(user);
        currency.setCode("EUR");
        currency.setName("Euro - EUR");
        currency.setId(snowflakeGenerator.next());
        currencyRepository.save(currency);
    }

}
