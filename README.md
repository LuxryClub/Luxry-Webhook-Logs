# Luxry Webhook Logs Plugin

The **Luxry Webhook Logs Plugin** is a Minecraft Bukkit plugin designed to send server logs to a specified Discord webhook. It provides a highly customizable embed format for Discord messages, allowing server administrators to log important events in style.

---

## Features

- **Discord Webhook Integration**: Send server log messages directly to your Discord server.
- **Customizable Embeds**:
  - Change the title, description, and color of the embed.
  - Add author, footer, thumbnail, and main images.
  - Enable or disable various embed sections.
- **Timestamp Support**: Add timestamps to embeds, with the option to use the current time.

---

## Configuration

Below is an example configuration file (`config.yml`) for the plugin:

```yaml
webhook:
  url: "YOUR_WEB_HOOK_URL" # The Discord webhook URL
  name: "Luxry Webhook Logs" # The name displayed in webhook logs

# Discord Embed Configuration
embed:
  # General Embed Settings
  title: "The system sent a new log message!" # Title of the embed
  description: "%log_here%" # Placeholder for the log message
  color: "#00FF00" # Embed color in HEX format (e.g., #00FF00 for green)

  # Author Section
  author:
    enabled: true # Toggle author section on/off
    name: "Luxry Club" # Author's name
    icon_url: "https://luxry.club/uploads/1730207085508.png" # URL to the author's icon
    url: "https://luxry.club" # URL linked to the author's name

  # Footer Section
  footer:
    enabled: true # Toggle footer section on/off
    text: "Powered by Luxry Club" # Footer text
    icon_url: "https://luxry.club/uploads/1730207085508.png" # URL to the footer icon

  # Thumbnail
  thumbnail:
    enabled: true # Toggle thumbnail on/off
    url: "https://luxry.club/uploads/1730207085508.png" # URL to the thumbnail image

  # Image
  image:
    enabled: true # Toggle image on/off
    url: "https://luxry.club/uploads/1730207085508.png" # URL to the main image

  # Timestamp
  timestamp:
    enabled: true # Toggle timestamp on/off
    use_current_time: true # Use the current time for the timestamp (true/false)
```

---

## Installation

1. Download the latest version of the plugin from the [Releases](https://github.com/your-repo/luxry-webhook-logs/releases) section.
2. Place the `.jar` file in your server's `plugins` folder.
3. Start your server to generate the `config.yml` file.
4. Configure the plugin by editing the `config.yml` file as desired.
5. Reload or restart your server to apply the changes.

---

## Usage

- Logs will automatically be sent to the configured Discord webhook in the format specified in `config.yml`.
- Use `%log_here%` in the `description` field to include the actual log message.

---

## Support

For support, visit the [Luxry Club Website](https://luxry.club) or open an issue in the [GitHub repository](https://github.com/your-repo/luxry-webhook-logs/issues).

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Contribution

We welcome contributions! Feel free to fork this repository and submit pull requests.

---

## Acknowledgments

- **Luxry Club** for providing inspiration and resources.
