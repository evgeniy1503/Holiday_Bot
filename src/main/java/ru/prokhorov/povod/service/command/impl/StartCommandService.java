package ru.prokhorov.povod.service.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.prokhorov.povod.enums.CommandEnums;
import ru.prokhorov.povod.service.HolidayService;
import ru.prokhorov.povod.service.command.CommandService;

import java.time.LocalDate;

/**
 * Сервис по обработке команды /start.
 *
 * @author Evgeniy_Prokhorov
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class StartCommandService implements CommandService {

    private final HolidayService holidayService;
    @Override
    public boolean isSupportCommand(String command) {
        return CommandEnums.START.getCommand().equals(command);
    }

    @Override
    public String createAnswer(Update update) {
        log.info("createAnswer() - start: update = {}", update);
        String forwardSenderName = update.getMessage().getFrom().getFirstName();
        return "Hello " + forwardSenderName + ". You started use Bot..." + holidayService.getHolidays("AT", LocalDate.now());
    }
}
