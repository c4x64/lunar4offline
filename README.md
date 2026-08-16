# Lunar4Offline

**Lunar4Offline** is a client-side Fabric mod for Minecraft **1.21.11** running on **Lunar Client**. It replaces Lunar's forced sign-in prompt with a Lunar-styled account menu and adds a small set of optional quality-of-life toggles.

It is not affiliated with Lunar Client, Mojang, or Microsoft.

## What it does

- **Account menu.** When Lunar requests a sign-in, Lunar4Offline shows a small in-game popup with two choices:
  - **Offline** — launch with an offline account profile (intended for offline-mode servers and local play).
  - **Microsoft** — open Lunar's original Microsoft sign-in flow instead.
- **Quieter startup.** The automatic sign-in prompt pushed at launch is suppressed, so it only appears when you open the account menu.
- **Nametag indicators.** An optional toggle that draws a small icon above players' heads, with size choices (8 / 12 / 16). The icon can be limited to offline players.

## Why use it

- Play on offline-mode or LAN servers without being forced through a Microsoft login each launch.
- Keep a real Microsoft account available when you want it, with everything managed from one menu.
- No Fabric API or other dependencies are required.

## Notes

- Installing or using this mod is at your own discretion and in accordance with the terms of Lunar Client, Mojang, and Microsoft.
- It does **not** grant access to online-mode servers with premium accounts, and it does **not** add skins or any multiplayer advantage.

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft **1.21.11**.
2. Place `lunar4offline-1.0.0.jar` in `~/.lunarclient/profiles/1.21/mods/fabric-1.21.11/`.
3. Launch Lunar on the **1.21 (Fabric)** profile and open the account menu from the top-left.

## Requirements

- Minecraft **1.21.11**
- Fabric Loader **>= 0.19.3**
- Java **21**
- Lunar Client

## License

MIT — see [LICENSE](LICENSE).