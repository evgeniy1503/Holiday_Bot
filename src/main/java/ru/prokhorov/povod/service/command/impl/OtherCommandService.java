package ru.prokhorov.povod.service.command.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.prokhorov.povod.aspect.logging.annotation.Logging;
import ru.prokhorov.povod.dto.PublicHoliday;
import ru.prokhorov.povod.enums.CommandEnums;
import ru.prokhorov.povod.service.CounterCodeService;
import ru.prokhorov.povod.service.HolidayService;
import ru.prokhorov.povod.service.command.CommandService;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис по обработке команды /other.
 *
 * @author Evgeniy_Prokhorov
 */
@Service
@RequiredArgsConstructor
public class OtherCommandService implements CommandService {

    private final CounterCodeService counterCodeService;
    private final HolidayService holidayService;

    @Override
    public boolean isSupportCommand(String command) {
        return CommandEnums.OTHER.getCommand().equals(command);
    }

    @Logging
    @Override
    public SendMessage createAnswer(Update update) {
        long chatId = update.getCallbackQuery().getFrom().getId();
        String counterCode;
        List<PublicHoliday> holidays;
        try {
            counterCode = counterCodeService.getCounterCode("Россия");
            holidays = holidayService.getHolidays(counterCode, LocalDate.now());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<PublicHoliday> holidayHow = holidays.stream()
                .filter(h -> h.getDate().equals("2025-01-03"))
                .toList();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(holidayHow.toString());

        return sendMessage;
    }
}
