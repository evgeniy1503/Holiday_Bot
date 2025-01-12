package ru.prokhorov.povod.service.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
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
    public SendMessage createAnswer(Update update) {
        log.info("createAnswer() - start: update = {}", update);
        String forwardSenderName = update.getMessage().getFrom().getFirstName();
        long chatId = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Hello " + forwardSenderName + "." + " Choose whose holidays you want to know for today.");

        // создаем объект встроенной клавиатуры
        InlineKeyboardMarkup keyboardMarkup = getInlineKeyboardMarkup();
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButtonCurrentCountry = new InlineKeyboardButton();
        inlineKeyboardButtonCurrentCountry.setText("My country");
        inlineKeyboardButtonCurrentCountry.setCallbackData("/start");

        InlineKeyboardButton inlineKeyboardButtonOtherCountry = new InlineKeyboardButton();
        inlineKeyboardButtonOtherCountry.setText("Other country");
        inlineKeyboardButtonOtherCountry.setCallbackData("/start");

        List<InlineKeyboardButton> rowsInlineList = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        rowsInlineList.add(inlineKeyboardButtonCurrentCountry);
        rowsInlineList.add(inlineKeyboardButtonOtherCountry);
        rowsInline.add(rowsInlineList);
        keyboardMarkup.setKeyboard(rowsInline);
        return keyboardMarkup;
    }
}
