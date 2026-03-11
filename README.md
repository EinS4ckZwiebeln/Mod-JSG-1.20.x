Public things from JSG 1.20


# **Just Stargate Mod**
***
Just Stargate Mod (JSG) is a Stargate-based Minecraft mod. It aims to closely resemble the franchise and be the most immersive Stargate mod out there.

[![Minecraft Versions](https://cf.way2muchnoise.eu/versions/For%20MC_537047_all.svg)](https://www.curseforge.com/minecraft/mc-mods/jsg/files)      [![Discord invite](https://img.shields.io/discord/881802052488011837?style=flat-square&label=%20&logo=discord&color=2D2D2D)](https://discord.justsgmod.eu) [![YouTube](https://img.shields.io/youtube/channel/subscribers/UChxSgOztJWUVqmw7TcZ-uMg?style=flat-square&label=%20&logo=youtube&logoColor=ff2129&color=2d2d2d)](https://www.youtube.com/channel/UChxSgOztJWUVqmw7TcZ-uMg) [![GitHub](https://img.shields.io/badge/-GitHub-2d2d2d?style=flat-square&logo=GitHub&logoColor=white)](https://github.com/MineDragonCZ/JSGMod) [![Website](https://img.shields.io/badge/-Website-2d2d2d?style=flat-square&logo=Pinboard&logoColor=00A95C)](https://justsgmod.eu/)
***
## **What is JSG?**
JSG is a mod originated on famous TV series called Stargate.
This mod adds devices like Stargates or Transport rings into the game.
You also will find big variety of materials for creating deviced mentioned before.
For example: Trinium, Titanium, Naquadah and more.
This mod also adds three Iris variants, so you can protect your base from unwanted guests.
***
## **How JSG works?**
JSG allows you to teleport anywhere in the world, even in other dimensions!
Transport rings work on short distance, like an elevator.
Tutorial can be found [here](https://www.youtube.com/watch?v=Adrj8sjAyC8).
If you prefer reading, you can try our [wiki](https://wiki.justsgmod.eu) (in 6 languages)
***
## **Repository-based feature overview**
This repository is the public API/resources snapshot for JSG 1.20.x, so the overview below is limited to features that are directly evident in the files in this repo.

### **Core progression and travel**
- Stargates are the center of the mod's progression loop. The API exposes Milky Way, Pegasus, Universe, Tollan, Orlin and Movie gate variants.
- Players build gates from separate base, chevron and ring blocks, then operate them with Dial Home Devices (DHDs) and crystals.
- Gate progression is supported by craftable Stargate fragments, iris upgrades, shield upgrades, glyph crystals, control crystals and GDO equipment.
- The advancement data shows a structured progression tree with 215 advancement files, including milestones for trading with priests, riding a Mastadge, building Orlin's gate and learning the mod's early-game systems.

### **Items and crafting materials**
- In this snapshot, the English localization defines **220 JSG item names**, covering raw ores, ingots, nuggets, dusts, plates, gears and utility components.
- Core materials include **Titanium, Trinium and Naquadah** in multiple processing stages, including raw, refined and alloyed Naquadah variants.
- Crystal-based components are a major part of crafting, with blue, red, ender, yellow, white and Pegasus crystals used for DHDs and address systems.
- Support items include notebook pages, cartridges, chalk, control circuits, shield emitters, pestles, chocolate bars, lemons and several music discs.

### **Blocks, machines and structures**
- In this snapshot, the localization defines **167 primary JSG block names**.
- Gate construction blocks are available for Milky Way, Pegasus, Universe, Tollan, Movie and Orlin Stargates, each with base, ring and chevron parts where applicable.
- DHD blocks exist for Milky Way and Pegasus networks, and the tooltips describe them as the control panels for dialing gates.
- Other named functional blocks in this snapshot include capacitor banks, redstone dial/state blocks, a toaster and a printer.
- Material storage and world blocks include Titanium, Trinium and Naquadah blocks plus budding crystal blocks for blue, red, ender, yellow, white and Pegasus crystal growth.
- World generation data defines **26 JSG structures**, including Abydos landmarks and multiple Stargate outposts for different environments such as plains, desert, snowy, badlands, mangrove, Nether, End and compatibility dimensions.

### **Ores and world generation**
- JSG adds **Titanium Ore, Trinium Ore and Naquadah Ore** along with deepslate, sand, netherrack and end stone variants for those resources.
- The included biome data shows an **Abydos dimension/region set** with three custom biomes: `abydos_desert`, `abydos_plain` and `abydos_naquadah_deposits`.
- The Abydos desert biome explicitly injects `jsg:naquadah_ore`, which reinforces Naquadah as a progression resource tied to exploration.
- Structure and template-pool data also indicate settlements, boulders, campfires, pyramids, dungeons and outposts spread across JSG-themed worldgen content.

### **Recipes**
- The data pack contains **603 recipe files**, so crafting is a large part of the mod.
- Verified recipe groups include DHD parts, Stargate fragments, iris upgrades, gears, crystals, dusts, ingots, nuggets, plates, utility items and food.
- The recipe set supports both crafting-table assembly and furnace/blast-furnace processing for ore and dust refinement.
- Example progression recipes in this repo include Titanium gears, DHD control crystals, Milky Way Stargate fragments and iris upgrades made from advanced materials.

### **NPCs, creatures and enemies**
- The clearest NPC evidence is the **Priest villager**, with desert, jungle, plains, savanna, snow, swamp and taiga profession variants used in advancements and worldgen pools.
- The clearest creature evidence is the **Mastadge**, which has a spawn egg and an advancement for riding it.
- Standard Minecraft hostile mob spawns still appear in the included biome data (for example zombie villagers in Abydos plains).
- This API/resources snapshot does **not** clearly document a broader list of custom hostile enemies, so it would be inaccurate to claim more enemy types from this repository alone.

### **Overall gameplay loop**
In practice, the files in this repository point to a progression loop of:
1. Explore worlds and JSG structures for resources and discoveries.
2. Mine and refine Titanium, Trinium and Naquadah.
3. Craft crystals, circuits, gears and gate fragments.
4. Assemble a Stargate and DHD network.
5. Upgrade that network with iris/shield technology and continue expanding into other worlds and structures.

If you want the most exhaustive player-facing guide, the official JSG wiki is still the best companion resource, but this repository already confirms a large feature set centered on Stargates, advanced materials, worldgen, structured crafting and dimension travel.
***
[![Trailer](https://img.youtube.com/vi/Ip-lWaQ3CnE/0.jpg)](https://www.youtube.com/watch?v=Ip-lWaQ3CnE)
***

> Created and coded by Tau'ri Dev Team<br>
> Models by MarcelMPL, **Harald de Luca** and from Aunis<br>
> Checkout our official website: [JustSGMod.eu](https://justsgmod.eu)<br>
> *Big thanks to MrJake222 for making base of this mod*<br>

# **Creating own JSG addon**
## Adding dependency
To create and addon you need to add our maven repository to your project:
``` gradle
repositories {
    maven {
        name = "jsg-api"
        url = "https://maven.justsgmod.eu/api/"
    }
}
```

And also add dependency:
``` gradle
dependencies {
    compileOnly "dev.tauri:jsg:[version]"
}
```

## Creating Loaders for Models and Textures
You can use our API to load custom OBJ models that are triangulated or custom (even custom sized or with custom format (png/jpg/jpeg)) textures.

Simply register your loaders:
``` java
public static final APIOBJLoader EXAMPLE_OBJ_LOADER = APIOBJLoader.createLoader(your mod id, main class of the mod);
public static final APITextureLoader EXAMPLE_TEXTURE_LOADER = APITextureLoader.createLoader(your mod id, main class of the mod);
```
You can check our example addon mod: https://github.com/Tau-ri-Dev/Example-JSG-1.20.x-Addon
