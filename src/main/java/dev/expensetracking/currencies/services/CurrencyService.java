package dev.expensetracking.currencies.services;

import dev.expensetracking.currencies.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDto> getCurrenciesForUser (Long userId);

}
