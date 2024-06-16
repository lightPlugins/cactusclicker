package io.lightplugins.cactusclicker.util;

import io.lightplugins.cactusclicker.Light;
import org.bukkit.Bukkit;

public class DebugPrinting {

    public void print(String message) {
        Bukkit.getConsoleSender().sendMessage(Light.consolePrefix + message);
    }
}
