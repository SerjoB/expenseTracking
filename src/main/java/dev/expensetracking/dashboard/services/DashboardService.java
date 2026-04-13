package dev.expensetracking.dashboard.services;

import dev.expensetracking.dashboard.dto.IncomeExpenseWidgetDto;

public interface DashboardService {

    IncomeExpenseWidgetDto getIncomeExpenseWidget(Long userId);

}
