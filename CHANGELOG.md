# Changelog

All notable public changes to Rail Junctions are documented here.

## 1.0.0 — First public release

- Adds Rail Cross, Dead End, Rail Switch, and Tee Junction Rail for Fabric / Minecraft 26.2.
- Adds minecart routing and stopping behavior for all four custom rail pieces.
- Stops minecarts before incorrectly aligned Rail Switch and Tee Junction routes instead of letting them slide off the track.
- Adds directional Dead End stopping.
- Includes standard vanilla crafting recipes and vanilla recipe-book unlocks.
- Places the custom rail recipes with the vanilla rail/equipment recipes while keeping the blocks in the Redstone creative tab.
- Rail Cross recipe returns 5 Rail Crosses; Dead End recipe returns 4 Dead Ends; Rail Switch and Tee Junction Rail recipes return 4 blocks each.
- Includes lightweight vanilla-style fallback block and item models.
- Updates the fallback Dead End with an oak-plank upper crossbar and metal supports that end cleanly beneath it instead of overlapping the wood.
- Adds a flatter, front-facing Dead End inventory/hotbar icon that fills the slot more clearly and stays closer to Minecraft's vanilla 2D item style.
- Supports the separately installed RAY's 3D Rails resource pack for compatible 3D models.
- Removes runtime dependencies on Little Logistics, Useful Railroads, and U-Team-Core.
- Uses the stable internal mod ID `railmod` for compatibility with existing worlds and resources.
- Includes LGPL-3.0-only project licensing, Apache-2.0 attribution for Useful Railroads-derived material, source references, and bundled third-party license notices.
- First-person and third-person held-item positioning for some 3D rail pieces is planned for further visual refinement in a future update.

### Development note

Internal pre-release builds used incremental `1.0.1`–`1.0.5` version numbers while recipes, metadata, licensing, and visuals were being finalized. None of those builds were published on Modrinth. The first public Modrinth version is therefore `1.0.0`.
