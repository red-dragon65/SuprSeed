
<p align="center">
<img src="DemoApp/src/main/res/drawable-v24/suprseed_widebanner.png" width=650 title="SuprSeed - Grow Games" alt="SuprSeed Engine Logo Banner">
</p>

---

<div align="center">

  [![](https://jitpack.io/v/red-dragon65/SuprSeed.svg)](https://jitpack.io/#red-dragon65/SuprSeed)

</div>

**SuprSeed is a light weight 2D Android game development framework.** SuprSeed offers flexibility and modularity, allowing it to become the foundation for mobile games, custom game engines, as well as UI driven applications.

The frameworks architecture is built following SOLID principles and Design Patterns. As a result, any component of the `Core` package can be swapped out to a custom implementation using the `EngineConfigurator` (which handles the engines dependency injection). The framework also comes with a library `Lib` package that offers default classes to help provide a starting point for creating games. This allows the framework to be used 'as is' or to be customized as needed.

For creating games, the engine uses scenes which can contain sub-scenes or sprites. Each sprite can then be configured using components and shared data. This creates a separation of concerns which reduces code complexity, and increases code reusability.

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

**Current Status:**
- first public release is available, more changes are being worked on

<br/>

**Expected major changes coming in version v1.0.0**
- package clean up
- image/animation api cleanup
- fix inconsistent exception handling
- rename interfaces to use the `I` naming convention
- improve the EngineConfigurator
- other stuff

<br/>

**Future**
- various improvements (refresh rate handling, fps counter, effects emitter, etc.)
- better docs (javadocs, wiki, guidelines, architecture etc.)

<br/>

Project Usage
---

**Initializing the project**
- Import the SuprSeed dependency via gradle
  - follow the steps at https://jitpack.io/#red-dragon65/SuprSeed
  - or, use the steps down below if you are using kotlin
- Create a new "Empty views activity" project in Android Studio.
- Remove the `<Textview ... />` from the `activity_main.xml` layout
- Remove the `onCreate()` function from the `MainActivity` class
- Update the `MainActivity` class to extend the `EngineActivity`
  - implement the methods as needed
- Add the `assets` folder
  - Right click the `app` folder -> `New` -> `Folder` -> `Assets Folder`
- The project is now ready to use the framework
  - use the repo's demo as a guide<sub> better docs will be available <sup>someday</sup></sub>

<br/>

**Dependency install for gradle + kotlin (.gradle.kts) files**

1. Add `maven { url = uri("https://jitpack.io") }` to your `settings.gradle.kts` file
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Include this repo
        maven { url = uri("https://jitpack.io") }
    }
}
```

2. Add `implementation("com.github.red-dragon65:SuprSeed:v0.1.0")` to your `build.gradle.kts` file

(note: this is the _Module:app_ level one)
```
dependencies {

    implementation(libs.androidx.core.ktx)
    ...
    androidTestImplementation(libs.androidx.espresso.core)
    // Add this dependency
    implementation("com.github.red-dragon65:SuprSeed:v0.1.0")
}
```


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
