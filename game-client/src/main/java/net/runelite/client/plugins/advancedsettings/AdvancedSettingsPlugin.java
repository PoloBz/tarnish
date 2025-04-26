package net.runelite.client.plugins.advancedsettings;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.entityhider.EntityHiderConfig;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
        name = "Advanced Settings",
        description = "Advanced settings for the Starlight Client",
        tags = {"npcs", "players"},
        enabledByDefault = false
)
@Slf4j
public class AdvancedSettingsPlugin extends Plugin {
    static final String CONFIG_GROUP = "advancedSettings";
    @Inject
    private Client client;
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private AdvancedSettingsOverlay advancedSettingsOverlay;
    @Provides
    AdvancedSettingsConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(AdvancedSettingsConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(advancedSettingsOverlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(advancedSettingsOverlay);
    }

}
