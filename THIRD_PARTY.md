# Third-party notices

Rail Junctions is a standalone Fabric mod distributed under **LGPL-3.0-only**. The project is not an official port of, and is not affiliated with or endorsed by, Little Logistics, Useful Railroads, U-Team, RAY's 3D Rails, Mojang, or Microsoft.

The project was developed under the working title **Rail Mod**. Its internal mod ID remains `railmod`; this document applies to the public project now named Rail Junctions.

This document records the upstream projects whose code/behavior was used as a reference during development and explains which material is and is not redistributed.

## Little Logistics

- Project: **Little Logistics** by Murad Akhundov and contributors
- Repository: https://github.com/MuradAkh/LittleLogistics
- Reference branch/version family: `main-1.21.1`
- Reference commit used for attribution: `68f97e61940bd05d59e7bc35c8d6ba14dd053053`
- Relevant upstream Java sources include:
  - `src/main/java/dev/murad/shipping/block/rail/SwitchRail.java`
  - `src/main/java/dev/murad/shipping/block/rail/TeeJunctionRail.java`
- Upstream license: **GNU Lesser General Public License v3.0** for `.java` files. The upstream LICENSE explicitly states that its LGPL terms cover Java files only.

Rail Junctions' `RailSwitchBlock.java` and `TeeJunctionRailBlock.java` are modified/reimplemented Fabric 26.2 implementations based on those rail behaviors. The corresponding Rail Junctions source files carry modification/attribution notices and are distributed under LGPL-3.0-only.

**No Little Logistics textures, models, sounds, logos, or other non-Java assets are bundled in Rail Junctions.**

## Useful Railroads

- Project: **Useful Railroads** by U-Team
- Repository: https://github.com/MC-U-Team/Useful-Railroads
- Reference version family: `1.21.1`
- Reference commit used for attribution: `0c6aeb737d391a3f719d49344f94fa94b62958af`
- Relevant upstream implementations include:
  - `forge/src/main/java/info/u_team/useful_railroads/block/IntersectionRailBlock.java`
  - `forge/src/main/java/info/u_team/useful_railroads/block/BufferStopBlock.java`
- Upstream license: **Apache License 2.0**
- Upstream copyright notice: `Copyright 2017-2025 U-Team (https://u-team.info)`
- The referenced `1.21.1` repository root contains a `LICENSE` file and no separate upstream `NOTICE` file.

Rail Junctions' `RailCrossBlock.java` and `DeadEndBlock.java` are modified/reimplemented Fabric 26.2 implementations based on those rail behaviors. The Dead End intentionally omits Useful Railroads' redstone/inventory/minecart-destruction functionality and only acts as a directional buffer stop.

A verbatim copy of the Useful Railroads Apache-2.0 license is included at:

`THIRD_PARTY_LICENSES/Useful-Railroads-Apache-2.0.txt`

**No Useful Railroads textures, models, sounds, logos, or other art assets are bundled in Rail Junctions.**

## RAY's 3D Rails

- Project: **RAY's 3D Rails** by xR4YM0ND
- Repository: https://github.com/xR4YM0ND/RAYs-3D-Rails
- Modrinth: https://modrinth.com/resourcepack/rays-3d-rails
- License: **MIT**

Rail Junctions contains compatibility shims so that the separately installed RAY's 3D Rails resource pack can override the four rail models. Rail Junctions does **not** bundle or redistribute RAY's textures or 3D models.

To provide this compatibility, Rail Junctions ships newly created lightweight fallback model JSON files under the `littlelogistics` and `usefulrailroads` resource namespaces. These fallback files use Minecraft vanilla texture/model references and intentionally reuse model identifiers recognized by RAY's 3D Rails. They are compatibility resources created for Rail Junctions, not copied upstream art assets.

## Minecraft / Fabric

Rail Junctions is a third-party Minecraft mod. Minecraft is developed by Mojang Studios and published by Microsoft. Fabric Loader and Fabric API are external runtime/build dependencies and are not bundled as source code in this repository.

## Source availability

The complete corresponding source for published Rail Junctions builds is this repository:

https://github.com/xDeadDreamsx/Rail-Junctions-Release

See `LICENSE` (LGPL-3.0) and `COPYING` (GPL-3.0, incorporated by LGPL-3.0) for the project license terms.
