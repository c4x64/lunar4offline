# SkipMenu — Lunar Client 1.21.11 Fabric mod

Offline login manager + stub custom module for Lunar Client, built with Fabric Loader (no Fabric API).

## Features

- **Account popup.** Whenever Lunar triggers a sign-in (top-left "add account" or any
  login push), the webosr sign-in **push is intercepted** (`WebosrSignInBridgeMixin`,
  method `ORIICIHIHHHIOHHHIHRHIOHHIHHHIR()`) and replaced with an in-game popup offering
  **Offline** or **Microsoft**:
  - `Offline` → username screen; applies a dynamic offline `Minecraft.User` (uuid derived
    from `OfflinePlayer:<name>`, token `0`) via `OfflineLogin`.
  - `Microsoft` → re-invokes the original Lunar sign-in (`@Invoker`, webosr overlay).
- **Gate bypass.** `AccountGateMixin` forces Lunar's is-logged-in boolean true so the
  sign-in overlay is not auto-pushed at startup (no popup spam); manual triggers still
  reach the popup.
- **Stub module.** `X` (id `x`) registered via `ModuleManager` — placeholder for future
  modules that will be surfaced in Lunar's UI. No behavior yet.
- **Config.** Persisted to `~/.lunarclient/offline/multiver/skipmenu-config.json`
  (offline username + module toggles).

## Target classes (Lunar 3.7.15, MC 1.21.11, verified from `lunar.jar`)

- Sign-in push (redirected):
  `com.moonsworth.lunar.client.IIICIHIRHRHOIIOICIOHRHRRHICIHC.ORCCOHCOCROCRCRIRHOIIHOCHCHHRH.ROHIRIOHCIROCRROIRHCIHOCIRORIR.IROIRHHRRCOCCHOOCOHHORCHHCHOCO.ORCCOHCOCROCRCRIRHOIIHOCHCHHRH.IROIRHHRRCOCCHOOCOHHORCHHCHOCO`
  → `public static void ORIICIHIHHHIOHHHIHRHIOHHIHHHIR()`
- Account gate (forced true):
  `com.moonsworth.lunar.client.ROCCORCCCIORIHHHHIRHIORRCRHIRR.ROHIRIOHCIROCRROIRHCIHOCIRORIR.IROIRHHRRCOCCHOOCOHHORCHHCHOCO`
  → `public boolean RIIHHCOICHHIOHRCHROIOHOOIIHICO()`

Targets are declared as string `targets` in `skipmenu.mixins.json` because the obfuscated
package/class names collide with other classes (javac cannot import them), and the classes
are loaded by Lunar's runtime classloader from `lunar.jar`.

## Build

```
./gradlew build
```

Deploy `build/libs/skipmenu-1.0.0.jar` to the profile mods folder(s), e.g.
`~/.lunarclient/profiles/1.21/mods/` and `fabric-1.21.11/`.