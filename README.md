
<p align="center">
<img src="app/src/main/res/drawable-v24/suprseed_widebanner.png" width=650 title="SuprSeed - Grow Games">
</p>

---

**SuprSeed is a light weight 2D Android game development engine.** The flexibility and modularity offered allows it to become the foundation of mobile games, custom game engines, as well as UI driven applications.

With the power of SOLID principles and Design Patterns, any component of the `Core` package can be swapped out to a custom implementation using the 'EngineConfigurator' (which handles the engines dependency injection). The framework also comes with a library `Lib` package that offers default classes to help provide a starting point for creating games.

For creating games, the engine uses scenes which can contain sub-scenes or sprites. Each sprite can then be configured using components and shared data. This allows a separation of concerns as well as code reusability to help create games faster.

<br/>

Some of the engines `Core` features are:
- Hardware accelerated drawing of bitmaps
- Bitmap resolution scaling across different screen sizes
- Coordinate scaling
- Logic tick rate / Screen refresh rate scaling
- Input handler

The engines `Lib` features are:
- Sound effect player
- Image loader
- Collision handling
- Font wrapper





<br/>

Progress
---

The project is currently around 95% complete. However, there are still a few quality of life features missing.

**Backlog:**
- Custom exception handling
- More logging for verbosity
- Game splash screen activity
- Game loading activity
- Java docs
- Getting started guide
- FPS counter

**Known Issues:**
- Changing the refresh rate is not currently supported due to incompatibilty with certain phones. A solution is currently being worked on.





<br/>

GameDemo Asset Info
---

*All assets used in the game demo came from [Itch.io](https://itch.io/)*

**Background**
- Name: [Grassy Mountains Parrallax Background](https://vnitti.itch.io/grassy-mountains-parallax-background)
- Author: [Vnitti](https://vnitti.itch.io/)
- Usage: Grassy Mountains (preview-fullcolor)

**Hero**
- Name: [Pixel Adventure](https://pixelfrog-assets.itch.io/pixel-adventure-1)
- Author: [Pixel Frog](https://pixelfrog-assets.itch.io/)
- Usage: Ninja frog

**Enemies**
- Name: [Pixel Adventure 2](https://pixelfrog-assets.itch.io/pixel-adventure-2)
- Author: [Pixel Frog](https://pixelfrog-assets.itch.io/)
- Usage: Bat, bee, bird, duck, ghost

**Font**
- Name: [Peaberry Pixel Font](https://emhuo.itch.io/peaberry-pixel-font)
- Author: [Emily Huo](https://emhuo.itch.io/)
- Usage: peaberry_base.tff

**Sounds**
- Name: [300+ Futuristic SFX](https://gamesupply.itch.io/300-futuristic-sfx-with-names)
- Author: [GameSupplyGuy](https://gamesupply.itch.io/)
- Usage: Get Extra Energy Level,  Mini Hit

**Music**
- Name: [Royalty Free Sounds](https://timbeek.itch.io/royalty-free-music-pack)
- Author: [Tim Beek](https://timbeek.itch.io/)
- Usage: 8Bit DNA Loop

*Assets are stored under the default project folders:*
- Images: `app/src/main/assets/Images`
- Font: `app/src/main/res/font`
- Sound: `app/src/main/res/raw`
