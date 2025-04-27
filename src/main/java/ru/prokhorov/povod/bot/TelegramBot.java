package ru.prokhorov.povod.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.prokhorov.povod.aspect.logging.annotation.Logging;
import ru.prokhorov.povod.config.BotConfig;
import ru.prokhorov.povod.service.command.CommandService;

import java.util.Optional;
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
    public void onUpdateReceived(final Update update) {
        Optional.ofNullable(getCommand(update))
                .ifPresent(t -> extracted(update, t));
    }

    @Logging
    public void extracted(final Update update, String command) {

        try {
            SendMessage answer = commandService.stream()
                    .filter(service -> service.isSupportCommand(command))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format(
                            "Для команды \"%s\" сервис обработки не найден.",
                            command
                    ))).createAnswer(update);
            execute(answer);
        } catch (Exception e) {
            sendMessage(null, "An error occurred while processing the request");
        }
    }

    private void sendMessage(final Long chatId,
                             final String textToSend) {
        System.out.println("Возникла ошибка при обработке!!!");
    }

    private String getCommand(final Update update) {
        String result = null;
        if (update.hasMessage()) {
            result = update.getMessage().getText();
        } else if (update.hasCallbackQuery()) {
            result = update.getCallbackQuery().getData();
        }
        return result;
    }
}
