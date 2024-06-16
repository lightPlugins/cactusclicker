package io.lightplugins.cactusclicker.exceptions;

import io.lightplugins.cactusclicker.Light;
import io.lightplugins.cactusclicker.util.interfaces.LightModule;

public class ModuleNotEnabledException extends Exception {

    public ModuleNotEnabledException(LightModule module) {
        super(Light.consolePrefix + "The Module §e" + module.getName() + "§r is not enabled");
    }
}
