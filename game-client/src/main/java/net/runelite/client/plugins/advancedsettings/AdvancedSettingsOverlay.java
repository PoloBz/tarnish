package net.runelite.client.plugins.advancedsettings;

import net.runelite.api.NPC;
import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.util.Text;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AdvancedSettingsOverlay extends Overlay {
    private static final int ACTOR_OVERHEAD_TEXT_MARGIN = 40;
    private static final int ACTOR_HORIZONTAL_TEXT_MARGIN = 10;
    private final AdvancedSettingsService advancedSettingsService;
    private final AdvancedSettingsConfig config;
    @Inject
    private AdvancedSettingsOverlay(AdvancedSettingsConfig config, AdvancedSettingsService advancedSettingsService) {
        this.config = config;
        this.advancedSettingsService = advancedSettingsService;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.MED);
    }
    @Override
    public Dimension render(Graphics2D graphics) {
        advancedSettingsService.forEachPlayer((player, color) -> renderPlayerOverlay(graphics, player, color));
        advancedSettingsService.forEachNpc((npc, color) -> renderNpcOverlay(graphics, npc, color));
        return null;
    }

    private void renderPlayerOverlay(Graphics2D graphics, Player actor, Color color) {
        final NameLocation nameLocation = config.playerNamePosition();
        if (nameLocation == NameLocation.DISABLED) {
            return;
        }


        final int zOffset;
        switch (nameLocation) {
            case MODEL_CENTER:
            case MODEL_RIGHT:
                zOffset = actor.getLogicalHeight() / 2;
                break;
            default:
                zOffset = actor.getLogicalHeight() + ACTOR_OVERHEAD_TEXT_MARGIN;
        }

        final String name = Text.sanitize(actor.getName());
        net.runelite.api.Point textLocation = actor.getCanvasTextLocation(graphics, name, zOffset);

        if (nameLocation == NameLocation.MODEL_RIGHT) {
            textLocation = actor.getCanvasTextLocation(graphics, "", zOffset);

            if (textLocation == null) {
                return;
            }

            textLocation = new net.runelite.api.Point(textLocation.getX() + ACTOR_HORIZONTAL_TEXT_MARGIN, textLocation.getY());
        }

        if (textLocation == null) {
            return;
        }

        BufferedImage rankImage = null;


        if (rankImage != null) {
            final int imageWidth = rankImage.getWidth();
            final int imageTextMargin;
            final int imageNegativeMargin;

            if (nameLocation == NameLocation.MODEL_RIGHT) {
                imageTextMargin = imageWidth;
                imageNegativeMargin = 0;
            } else {
                imageTextMargin = imageWidth / 2;
                imageNegativeMargin = imageWidth / 2;
            }

            final int textHeight = graphics.getFontMetrics().getHeight() - graphics.getFontMetrics().getMaxDescent();
            final net.runelite.api.Point imageLocation = new net.runelite.api.Point(textLocation.getX() - imageNegativeMargin - 1, textLocation.getY() - textHeight / 2 - rankImage.getHeight() / 2);
            OverlayUtil.renderImageLocation(graphics, imageLocation, rankImage);

            // move text
            textLocation = new Point(textLocation.getX() + imageTextMargin, textLocation.getY());
        }

        OverlayUtil.renderTextLocation(graphics, textLocation, name, color);
    }

    private void renderNpcOverlay(Graphics2D graphics, NPC actor, Color color) {
        final NameLocation nameLocation = config.npcNamePosition();
        if (nameLocation == NameLocation.DISABLED) {
            return;
        }


        final int zOffset;
        switch (nameLocation) {
            case MODEL_CENTER:
            case MODEL_RIGHT:
                zOffset = actor.getLogicalHeight() / 2;
                break;
            default:
                zOffset = actor.getLogicalHeight() + ACTOR_OVERHEAD_TEXT_MARGIN;
        }

        final String name = Text.sanitize(actor.getName());
        net.runelite.api.Point textLocation = actor.getCanvasTextLocation(graphics, name, zOffset);

        if (nameLocation == NameLocation.MODEL_RIGHT) {
            textLocation = actor.getCanvasTextLocation(graphics, "", zOffset);

            if (textLocation == null) {
                return;
            }

            textLocation = new net.runelite.api.Point(textLocation.getX() + ACTOR_HORIZONTAL_TEXT_MARGIN, textLocation.getY());
        }

        if (textLocation == null) {
            return;
        }

        BufferedImage rankImage = null;


        if (rankImage != null) {
            final int imageWidth = rankImage.getWidth();
            final int imageTextMargin;
            final int imageNegativeMargin;

            if (nameLocation == NameLocation.MODEL_RIGHT) {
                imageTextMargin = imageWidth;
                imageNegativeMargin = 0;
            } else {
                imageTextMargin = imageWidth / 2;
                imageNegativeMargin = imageWidth / 2;
            }

            final int textHeight = graphics.getFontMetrics().getHeight() - graphics.getFontMetrics().getMaxDescent();
            final net.runelite.api.Point imageLocation = new net.runelite.api.Point(textLocation.getX() - imageNegativeMargin - 1, textLocation.getY() - textHeight / 2 - rankImage.getHeight() / 2);
            OverlayUtil.renderImageLocation(graphics, imageLocation, rankImage);

            // move text
            textLocation = new Point(textLocation.getX() + imageTextMargin, textLocation.getY());
        }

        OverlayUtil.renderTextLocation(graphics, textLocation, name, color);
    }
}
