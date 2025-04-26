package net.runelite.client.plugins.advancedsettings;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Player;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.util.Arrays;
import java.util.function.BiConsumer;

@Singleton
public class AdvancedSettingsService {
    private final Client client;
    private final AdvancedSettingsConfig config;

    @Inject
    private AdvancedSettingsService(Client client, AdvancedSettingsConfig config) {
        this.config = config;
        this.client = client;
    }

    public void forEachPlayer(final BiConsumer<Player, Color> consumer) {
        if (!config.localPlayerName()
                && !config.playerNames()) {
            return;
        }

        final Player localPlayer = client.getLocalPlayer();

        for (Player player : client.getPlayers()) {
            if (player == null || player.getName() == null) {
                continue;
            }

            if (player == localPlayer) {
                if (config.localPlayerName()) {
                    System.out.println("Rendering local player name: "+player.getName());
                    consumer.accept(player, config.getOwnPlayerColor());
                }
            } else if (config.playerNames()) {
                System.out.println("Rendering player name: "+player.getName());
                consumer.accept(player, config.getOtherPlayerColor());
            }
        }
    }

    public void forEachNpc(final BiConsumer<NPC, Color> consumer) {
        if (!config.npcNames()) {
            return;
        }


        for (NPC npc : client.getNpcs()) {
            if (npc == null || npc.getName() == null) {
                continue;
            }
            consumer.accept(npc, config.getNpcColor());
        }
    }
}
