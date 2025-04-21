package ru.prokhorov.povod.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурации бота.
 *
 * @author Evgeniy_Prokhorov
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "bot")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfig {

    String name;

    String token;
}
