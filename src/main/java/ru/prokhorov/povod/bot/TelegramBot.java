package ru.prokhorov.povod.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.prokhorov.povod.config.BotConfig;
import ru.prokhorov.povod.service.command.CommandService;

import java.util.Set;

/**
 * TelegramBot.
 *
 * @author Evgeniy_Prokhorov
 */
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    private final Set<CommandService> commandService;

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            try {
                String answer = commandService.stream()
                        .filter(service -> service.isSupportCommand(messageText))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException(String.format(
                                "Для команды \"%s\" сервис обработки не найден.",
                                messageText
                        ))).createAnswer(update);
                sendMessage(chatId, answer);
            } catch (Exception e) {
                System.out.println(e.fillInStackTrace());
                sendMessage(chatId, "Bot ERROR!!!");
            }
        }

    }

    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}
