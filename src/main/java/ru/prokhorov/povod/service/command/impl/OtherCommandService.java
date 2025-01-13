package ru.prokhorov.povod.service.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.prokhorov.povod.enums.CommandEnums;
import ru.prokhorov.povod.service.command.CommandService;

/**
 * Сервис по обработке команды /other.
 *
 * @author Evgeniy_Prokhorov
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class OtherCommandService implements CommandService {
    @Override
    public boolean isSupportCommand(String command) {
        return CommandEnums.OTHER.getCommand().equals(command);
    }

    @Override
    public SendMessage createAnswer(Update update) {
        log.info("createAnswer() - start: update = {}", update);
        long chatId = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Функционал на стадии реализации");

        return sendMessage;
    }
}
