# SlashServer

Allows your players to use `/<servername>` command to switch subservers instead of `/server <servername>`

## Links and support
- [GitHub issue tracker](https://github.com/MTM123/SlashServer/issues)
- [Discord](https://discord.gg/4zPACSd87X)

## Requirements

- Velocity 3.0.0+ or BungeeCord

## Compiling

1. Clone the repository
2. Run `./gradlew build` in the main directory
3. Output file will be located in `build/libs` directory

## Config

Example config:
```yaml
servers:
-   server: lobby
    # Aliases to /lobby command - you can use listed command instead of /lobby
    commands:
    - hub
-   server: factions
    commands:
    - faction
    - fac
```
