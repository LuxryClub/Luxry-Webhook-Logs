package club.luxry;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedAuthor;
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedFooter;
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedField;
import org.bukkit.configuration.file.FileConfiguration;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscordEmbedLoader {
    /*
    * TODO OPTIMIZE THE CODE SO IT DOESN'T LOAD THE CONFIG EACH TIME
    * */

    public static WebhookEmbed loadEmbed(FileConfiguration config, String message) {
        // Embed Section
        String title = config.getString("embed.title");
        String description = message;
        String hexColor = config.getString("embed.color");
        Integer color = hexColor != null ? Color.decode(hexColor).getRGB() : null;

        // Timestamp
        OffsetDateTime timestamp = null;
        if (config.getBoolean("embed.timestamp.enabled", false)) {
            boolean useCurrentTime = config.getBoolean("embed.timestamp.use_current_time", true);
            if (useCurrentTime) {
                timestamp = OffsetDateTime.now();
            }
        }

        // Author Section
        EmbedAuthor author = null;
        if (config.getBoolean("embed.author.enabled", false)) {
            String authorName = config.getString("embed.author.name");
            String authorIconUrl = config.getString("embed.author.icon_url");
            String authorUrl = config.getString("embed.author.url");
            if (authorName != null) {
                author = new EmbedAuthor(authorName, authorIconUrl, authorUrl);
            }
        }

        // Footer Section
        EmbedFooter footer = null;
        if (config.getBoolean("embed.footer.enabled", false)) {
            String footerText = config.getString("embed.footer.text");
            String footerIconUrl = config.getString("embed.footer.icon_url");
            if (footerText != null) {
                footer = new EmbedFooter(footerText, footerIconUrl);
            }
        }

        // Thumbnail
        String thumbnailUrl = config.getBoolean("embed.thumbnail.enabled", false)
                ? config.getString("embed.thumbnail.url")
                : null;

        // Image
        String imageUrl = config.getBoolean("embed.image.enabled", false)
                ? config.getString("embed.image.url")
                : null;

        // Fields (Optional)
        List<EmbedField> fields = new ArrayList<>(); // You can add fields if needed.

        // Construct and return the WebhookEmbed
        return new WebhookEmbed(
                timestamp,
                color,
                description,
                thumbnailUrl,
                imageUrl,
                footer,
                title != null ? new WebhookEmbed.EmbedTitle(title, null) : null,
                author,
                fields
        );
    }
}
