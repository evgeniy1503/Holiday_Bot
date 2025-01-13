package ru.prokhorov.povod.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
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
                SendMessage answer = commandService.stream()
                        .filter(service -> service.isSupportCommand(messageText))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException(String.format(
                                "Для команды \"%s\" сервис обработки не найден.",
                                messageText
                        ))).createAnswer(update);
                execute(answer);
            } catch (Exception e) {
                sendMessage(chatId, "An error occurred while processing the request");
            }
        }

    }

    private void sendMessage(final Long chatId,
                             final String textToSend) {
        System.out.println("Возникла ошибка при обработке!!!");
    }
}
