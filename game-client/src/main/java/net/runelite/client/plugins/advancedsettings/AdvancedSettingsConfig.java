package net.runelite.client.plugins.advancedsettings;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup(AdvancedSettingsPlugin.CONFIG_GROUP)
public interface AdvancedSettingsConfig extends Config {

    @ConfigItem(
            position = 1,
            keyName = "playerNames",
            name = "Other Player Names",
            description = "Configures whether or not other player names are displayed"
    )
    default boolean playerNames()
    {
        return true;
    }

    @ConfigItem(
            position = 2,
            keyName = "localPlayerName",
            name = "Local Player Name",
            description = "Configures whether or not local player name is displayed"
    )
    default boolean localPlayerName()
    {
        return true;
    }

    @ConfigItem(
            position = 3,
            keyName = "npcNames",
            name = "NPC Names",
            description = "Configures whether or not NPC names are displayed"
    )
    default boolean npcNames()
    {
        return false;
    }

    @ConfigItem(
            position = 4,
            keyName = "localPlayerColor",
            name = "Local player",
            description = "Color of your own player"
    )
    default Color getOwnPlayerColor() {
        return new Color(0, 184, 212);
    }

    @ConfigItem(
            position = 5,
            keyName = "otherPlayerColor",
            name = "Other players",
            description = "Color of other players"
    )
    default Color getOtherPlayerColor() {
        return Color.RED;
    }

    @ConfigItem(
            position = 6,
            keyName = "npcColor",
            name = "NPCs",
            description = "Color of NPCs"
    )
    default Color getNpcColor() {
        return Color.YELLOW;
    }



    @ConfigItem(
            position = 7,
            keyName = "playerNamePosition",
            name = "Name position",
            description = "Configures the position of drawn player names, or if they should be disabled"
    )
    default NameLocation playerNamePosition() {
        return NameLocation.ABOVE_HEAD;
    }

    @ConfigItem(
            position = 8,
            keyName = "npcNamePosition",
            name = "NPC Name position",
            description = "Configures the position of drawn NPC names, or if they should be disabled"
    )
    default NameLocation npcNamePosition() {
        return NameLocation.ABOVE_HEAD;
    }
}
