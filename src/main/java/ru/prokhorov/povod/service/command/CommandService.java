package ru.prokhorov.povod.service.command;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Интерфейс для команд бота.
 *
 * @author Evgeniy_Prokhorov
 */
public interface CommandService {

    boolean isSupportCommand(String command);

    String createAnswer(Update update);
}
