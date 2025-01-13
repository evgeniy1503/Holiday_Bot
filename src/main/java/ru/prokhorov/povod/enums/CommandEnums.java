package ru.prokhorov.povod.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * CommandEnums.
 *
 * @author Evgeniy_Prokhorov
 */
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CommandEnums {

    START("/start"),
    CURRENT("/current"),
    OTHER("/other");

    String command;
}
