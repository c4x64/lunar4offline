# Lunar4Offline

**Login freely on Lunar Client.** Lunar4Offline is a client-side Fabric mod for Minecraft **1.21.11** on Lunar Client that lets you log in as an offline player, adds a Lunar-styled account popup (Offline / Microsoft), and gives you a module system with nametag indicators.

![Lunar4Offline](logo.png)

## Features

- **Offline login.** Replace Lunar's sign-in screen with an in-game popup. Log in as any offline username — no Microsoft account needed.
- **Account popup.** Whenever Lunar triggers a sign-in, the push is intercepted and you get a clean choice: **Offline** or **Microsoft**.
- **Microsoft fallback.** Still want a real account? The popup re-invokes Lunar's original sign-in overlay.
- **No startup popup spam.** The auto-push at launch is suppressed; manual triggers still open the popup.
- **Nametag module.** Toggle a lunar icon shown above players (offline player only, or everyone), with configurable size (8 / 12 / 16).
- **Config file.** Settings are persisted to `~/.lunarclient/offline/multiver/skipmenu-config.json`.

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for **1.21.11**.
2. Copy `lunar4offline-1.0.0.jar` into `~/.lunarclient/profiles/1.21/mods/fabric-1.21.11/`.
3. Launch the **1.21** (Fabric) Lunar profile and open the account popup from the top-left.

> No Fabric API is required.

## Requirements

- Minecraft **1.21.11**
- Fabric Loader **>= 0.19.3**
- Java **21**
- Lunar Client

## License

MIT — see [LICENSE](LICENSE).