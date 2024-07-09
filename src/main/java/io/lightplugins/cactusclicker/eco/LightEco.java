package io.lightplugins.cactusclicker.eco;

import io.lightplugins.cactusclicker.Light;
import io.lightplugins.cactusclicker.eco.commands.DummyCommand;
import io.lightplugins.cactusclicker.eco.config.MessageParams;
import io.lightplugins.cactusclicker.eco.config.SettingParams;
import io.lightplugins.cactusclicker.eco.manager.QueryManager;
import io.lightplugins.cactusclicker.util.SubCommand;
import io.lightplugins.cactusclicker.util.interfaces.LightModule;
import io.lightplugins.cactusclicker.util.manager.CommandManager;
import io.lightplugins.cactusclicker.util.manager.FileManager;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;

import java.util.ArrayList;

public class LightEco implements LightModule {

    public static LightEco instance;
    public boolean isModuleEnabled = false;
    public Economy economy = null;
    private QueryManager queryManager;
    public static Economy economyVaultService;

    public final String moduleName = "eco";
    public final String adminPerm = "light." + moduleName + ".admin";
    public final static String tablePrefix = "lighteco_";
    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    @Getter
    private SettingParams settingParams;
    @Getter
    private static MessageParams messageParams;
    @Getter
    private FileManager settings;
    @Getter
    private FileManager language;

    @Override
    public void enable() {
        Light.getDebugPrinting().print(
                "Starting the core module " + this.moduleName);
        instance = this;
        Light.getDebugPrinting().print(
                "Creating default files for core module " + this.moduleName);
        initFiles();
        this.settingParams = new SettingParams(this);
        Light.getDebugPrinting().print(
                "Selecting module language for core module " + this.moduleName);
        selectLanguage();
        messageParams = new MessageParams(language);
        Light.getDebugPrinting().print(
                "Registering subcommands for core module " + this.moduleName + "...");
        initSubCommands();
        this.isModuleEnabled = true;
        Light.getDebugPrinting().print(
                "Successfully started core module " + this.moduleName + "!");

        if(!initDatabase()) {
            Light.getDebugPrinting().print("§4Failed to initialize start sequence while enabling module §c" + this.moduleName);
            disable();
        }


        registerEvents();

        //getQueryManager().createEcoTable();

    }

    @Override
    public void disable() {
        this.isModuleEnabled = false;
        Light.getDebugPrinting().print("Disabled module " + this.moduleName);
    }

    @Override
    public void reload() {
        //initFiles();
        getSettings().reloadConfig(moduleName + "/settings.yml");
        Light.getDebugPrinting().print(moduleName + "/settings.yml");
        selectLanguage();
        Light.getDebugPrinting().print(moduleName + "/language/" + settingParams.getModuleLanguage() + ".yml");
        getLanguage().reloadConfig(moduleName + "/language/" + settingParams.getModuleLanguage() + ".yml");
    }

    @Override
    public boolean isEnabled() {
        return this.isModuleEnabled;
    }

    @Override
    public String getName() {
        return moduleName;
    }

    private void selectLanguage() {
        this.language = Light.instance.selectLanguage(settingParams.getModuleLanguage(), moduleName);
    }

    private void initFiles() {
        this.settings = new FileManager(
                Light.instance, moduleName + "/settings.yml", true);
    }

    private void initSubCommands() {
        PluginCommand ecoCommand = Bukkit.getPluginCommand("eco");
        subCommands.add(new DummyCommand());
        new CommandManager(ecoCommand, subCommands);

    }

    public QueryManager getQueryManager() { return this.queryManager; }

    public void registerEvents() {
        //Bukkit.getPluginManager().registerEvents(new CreatePlayerOnJoin(), Light.instance);
    }

    private boolean initDatabase() {
        this.queryManager = new QueryManager(Light.instance.getConnection());
        queryManager.createEcoTable();
        return true;
    }
}
