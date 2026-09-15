# Modrinth publishing sheet — Rail Junctions

This file contains the recommended values for the first public Modrinth publication.

## Project metadata

- **Project name:** Rail Junctions
- **Suggested slug:** `rail-junctions`
- **Project type:** Mod
- **Summary:** Adds vanilla-style rail switches, T-junctions, crossings, and buffer stops for minecarts, with optional RAY's 3D Rails compatibility.
- **Categories:** Transportation, Utility
- **Client side:** Required
- **Server side:** Required
- **License:** LGPL-3.0-only
- **Source code:** https://github.com/xDeadDreamsx/Rail-Junctions-Release
- **Issue tracker:** https://github.com/xDeadDreamsx/Rail-Junctions-Release/issues

## First public version

- **Version name:** Rail Junctions 1.0.0
- **Version number:** `1.0.0`
- **Version type:** Release
- **Game version:** Minecraft Java Edition 26.2
- **Loader:** Fabric
- **Primary file:** `rail-junctions-1.0.0.jar`

## Dependencies

### Required

- **Fabric API** — required

### Optional

- **RAY's 3D Rails** — optional resource pack for supported 3D rail models
  - https://modrinth.com/resourcepack/rays-3d-rails

### Do not add as dependencies

- Little Logistics
- Useful Railroads
- U-Team-Core
- JEI

Rail Junctions is standalone with respect to those projects. JEI can display the normal vanilla crafting recipes without a hard dependency.

## Recommended project description

# Rail Junctions

**Rail Junctions** adds four compact, vanilla-style rail pieces for minecart networks on Fabric:

- **Rail Cross** — a four-way crossing that lets minecarts continue straight across.
- **Rail Switch** — a three-way switch controlled by redstone; sneak-right-click changes the branch side.
- **Tee Junction Rail** — a T-junction whose active branch is selected by redstone.
- **Dead End** — a directional buffer stop that safely stops minecarts at the end of a line.

Minecarts approaching a Rail Switch or Tee Junction from a side that is not connected by the currently selected route stop on the preceding rail instead of sliding off the track.

## Requirements

- Minecraft Java Edition 26.2
- Fabric Loader 0.19.5+
- Fabric API
- Java 25

Little Logistics, Useful Railroads and U-Team-Core are **not required**.

## Crafting

All four blocks use standard vanilla crafting recipes and unlock in the vanilla recipe book. Recipe-viewer mods such as JEI can discover them as ordinary crafting recipes.

- **Rail Cross:** 5 vanilla Rails in a cross pattern → 5 Rail Crosses
- **Dead End:** iron ingots + sticks → 4 Dead Ends
- **Rail Switch:** 4 vanilla Rails → 4 Rail Switches
- **Tee Junction Rail:** 4 vanilla Rails → 4 Tee Junction Rails

## Visuals and RAY's 3D Rails compatibility

Rail Junctions includes lightweight fallback models using vanilla texture references. The fallback Dead End uses an oak-plank upper crossbar, cleanly separated metal supports, and a flatter front-facing inventory/hotbar icon designed to stay closer to Minecraft's vanilla 2D item style.

Rail Junctions is also optionally compatible with **RAY's 3D Rails**. When that resource pack is installed, its supported 3D models replace the corresponding fallback models.

RAY's 3D Rails is a separate project and is not bundled with Rail Junctions:
https://modrinth.com/resourcepack/rays-3d-rails

## Planned visual polish

The mod is functionally complete for its first public release, but the held-item positioning of some 3D rail pieces is still being polished, especially in first-person and third-person views. This is a visual issue only and does not affect placement, crafting, or minecart behavior. A future update is planned to refine these hand positions.

## Open source, licensing and attribution

Rail Junctions is open source under **LGPL-3.0-only**.

Parts of the rail behavior are adapted/reimplemented from:

- **Little Logistics** by Murad Akhundov and contributors — relevant Java sources licensed under LGPL-3.0.
- **Useful Railroads** by U-Team — licensed under Apache-2.0.

No original Little Logistics or Useful Railroads art assets are bundled. No RAY's 3D Rails assets are bundled or redistributed.

Full source, license texts, attribution, upstream references, and modification notes are available in this GitHub repository.

See in particular `LICENSE`, `COPYING`, `NOTICE`, and `THIRD_PARTY.md`.

Rail Junctions is an independent third-party project. It is not an official port and is not affiliated with or endorsed by Little Logistics, Useful Railroads/U-Team, RAY's 3D Rails, Mojang, or Microsoft.

## Recommended 1.0.0 version changelog

### Rail Junctions 1.0.0

First public release of **Rail Junctions**.

- Adds Rail Cross, Dead End, Rail Switch, and Tee Junction Rail.
- Includes working minecart routing/stopping behavior for all four custom rails.
- Stops minecarts before incorrectly aligned Switch/Tee routes.
- Includes vanilla-style fallback models and item icons.
- Gives the fallback Dead End an oak-plank upper crossbar with metal supports that no longer overlap it.
- Uses a flatter, front-facing Dead End inventory/hotbar icon that fills the slot more clearly and stays closer to vanilla 2D item styling.
- Supports the separately installed RAY's 3D Rails resource pack.
- Includes standard vanilla crafting recipes and recipe-book unlocks.
- Uses the stable internal mod ID `railmod` for compatibility with existing worlds and resources.
- Includes complete LGPL-3.0 / Apache-2.0 attribution and source availability information.
- Held-item positioning for some 3D rail pieces is planned for further visual refinement in a later update.

**Required:** Fabric API  
**Minecraft:** 26.2  
**Loader:** Fabric

## Upload checklist

Before clicking Publish on Modrinth:

1. Upload `rail-junctions-1.0.0.jar` as the primary file.
2. Select Minecraft 26.2 and Fabric.
3. Mark Fabric API as a required dependency.
4. Optionally add RAY's 3D Rails as an optional dependency/resource-pack relation.
5. Select LGPL-3.0-only as the project license.
6. Add the GitHub source and issue-tracker links above.
7. Paste the project description and 1.0.0 changelog from this file.
8. Do not mark Little Logistics, Useful Railroads, U-Team-Core, or JEI as required dependencies.
