package ru.prokhorov.povod.service.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.prokhorov.povod.aspect.logging.annotation.Logging;
import ru.prokhorov.povod.enums.CommandEnums;
import ru.prokhorov.povod.service.HolidayService;
import ru.prokhorov.povod.service.command.CommandService;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис по обработке команды /start.
 *
 * @author Evgeniy_Prokhorov
 */

@Logging
@Service
@RequiredArgsConstructor
public class StartCommandService implements CommandService {

    private final HolidayService holidayService;

    @Override
    public boolean isSupportCommand(String command) {
        return CommandEnums.START.getCommand().equals(command);
    }

    @Override
    public SendMessage createAnswer(Update update) {
        String forwardSenderName = update.getMessage().getFrom().getFirstName();
        long chatId = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Привет " + forwardSenderName + "." + " Выбери страну для получения праздника на сегодня.");

        // создаем объект встроенной клавиатуры
        InlineKeyboardMarkup keyboardMarkup = getInlineKeyboardMarkup();
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButtonCurrentCountry = new InlineKeyboardButton();
        inlineKeyboardButtonCurrentCountry.setText("\uD83C\uDDF7\uD83C\uDDFA");
        inlineKeyboardButtonCurrentCountry.setCallbackData(CommandEnums.CURRENT.getCommand());

        InlineKeyboardButton inlineKeyboardButtonOtherCountry = new InlineKeyboardButton();
        inlineKeyboardButtonOtherCountry.setText("\uD83C\uDF0D");
        inlineKeyboardButtonOtherCountry.setCallbackData(CommandEnums.OTHER.getCommand());

        List<InlineKeyboardButton> rowsInlineList = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        rowsInlineList.add(inlineKeyboardButtonCurrentCountry);
        rowsInlineList.add(inlineKeyboardButtonOtherCountry);
        rowsInline.add(rowsInlineList);
        keyboardMarkup.setKeyboard(rowsInline);
        return keyboardMarkup;
    }
}
