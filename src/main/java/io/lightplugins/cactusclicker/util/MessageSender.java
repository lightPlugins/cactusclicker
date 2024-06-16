package io.lightplugins.cactusclicker.util;
import io.lightplugins.cactusclicker.Light;
import io.lightplugins.cactusclicker.eco.LightEco;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageSender {

    public void sendPlayerMessage(String message, Player player) {
        Bukkit.getScheduler().runTask(Light.instance, () -> {
            Audience audience = (Audience) player;
            Component component = Light.instance.colorTranslation.universalColor(LightEco.getMessageParams().prefix() + message);
            audience.sendMessage(component);
        });
    }
}
