## Changelog

### Demo changelog

Note: the demo is not a part of this release and is merely used as a test sandbox for the game engine. Regardless, below are a list of the major changes that affect demo functionality since the first release. The below changes may or may not reflect various engine changes that have occurred.

♻️ **Changed** ([0dd60ac9](https://github.com/red-dragon65/SuprSeed/commit/0dd60ac9))

- refactored demo app files to make scene separation more clear

♻️ **Changed** ([c1157588](https://github.com/red-dragon65/SuprSeed/commit/c1157588))

- updated scenes to add and remove touch listeners in the scenes onPost/onDestroy methods in order to avoid any weird behaviors

♻️ **Changed** ([a4011822](https://github.com/red-dragon65/SuprSeed/commit/a4011822))

- consolidated fonts to use the `FontSprite` and `FontPaintRoller` in order to reduce code duplication

♻️ **Changed** ([05946560](https://github.com/red-dragon65/SuprSeed/commit/05946560))

- the demo now handles game playing and pausing by using the `WindowEventRegistry`

✨ **Added** ([8c6c6203](https://github.com/red-dragon65/SuprSeed/commit/8c6c6203), [addb973d](https://github.com/red-dragon65/SuprSeed/commit/addb973d))

- Added new scenes: MainScene, menuScene, GameLoadSpinner

🛠️ **Fixed** ([cf216236](https://github.com/red-dragon65/SuprSeed/commit/cf216236))

- fixed a mis-configuration where the main scene was being instantiated twice (once in the `MainActivity`, and again in the `ClientEngineConfigurator`)
  - fixes an issue where the instantiation in `MainActivity` was not actually being used

🛠️ **Fixed** ([95a6044d](https://github.com/red-dragon65/SuprSeed/commit/95a6044d))

- fixed an issue where the demo app was not pausing background music if the device got locked

<br/>

<br/>

## Engine Changelog

🚨 Breaking - ♻️ Changes - ✨ Feature - 🛠️ Fix

**Note:** recommended actions are in 🚨 _italics_

<br/>

♻️ **Changed** ([5f6e7a11](https://github.com/red-dragon65/SuprSeed/commit/5f6e7a11), [d5d75b61](https://github.com/red-dragon65/SuprSeed/commit/d5d75b61))

- updated gradle to version 8.9
- added java tool chain target 17 to gradle

♻️ **Change** ([659383e8](https://github.com/red-dragon65/SuprSeed/commit/659383e8))

- loop pausing and resuming has now moved to `LoopController`
- 🚨 _the client should use `EngineTools.getLoopController()` as the `LoopRunner` is no longer a singleton object_

✨ **Added** ([c1157588](https://github.com/red-dragon65/SuprSeed/commit/c1157588))

- the `SceneBackgroundLoader` now provides a method for shutting down the executor thread
- updated the `EngineActivity` to destroy scenes when the Android applications destroy method gets called

✨ **Added** ([a4011822](https://github.com/red-dragon65/SuprSeed/commit/a4011822))

- added a `FontSprite` to act as a default sprite for making text rendering more straight forward
- updated `FontPaintRoller` to make it more straight forward when rendering fonts by providing a `renderFont()` method

♻️ **Changed** ([05946560](https://github.com/red-dragon65/SuprSeed/commit/05946560))

- fixed an issue where the engine was pausing the game after gaining focus rather then letting the demo handle pause and resume
- added a `WindowEventListener` and `WindowEventRegistry` to allow handling window change events
- 🚨 _the client should register to the `WindowEventRegistry` in order to handle pausing and resuming when window focus changes_

✨ **Added** ([347ce5fe](https://github.com/red-dragon65/SuprSeed/commit/347ce5fe))

- the `SubSceneRegistry` now allows scenes to be removed and retrieved based on scene id

✨ **Added** ([addb973d](https://github.com/red-dragon65/SuprSeed/commit/addb973d))

- created the `ScenePreemptLoad` class that allows the user to initialize scenes in the background for later use

🛠️ **Fixed** ([addb973d](https://github.com/red-dragon65/SuprSeed/commit/addb973d))

- fixed a potential race condition that could occur in `SceneHardChange`

✨ **Added** ([1fdf2089](https://github.com/red-dragon65/SuprSeed/commit/1fdf2089))

- `SceneHardChange` now allows the user to specify a minimum show time for loading screens

🛠️ **Fixed** ([8c6c6203](https://github.com/red-dragon65/SuprSeed/commit/8c6c6203))

- fixed an issue where `SceneManager` was ignoring `isDrawable` and `isActive` statuses for sub scenes
- updated the `SubSceneRegistry` to prevent duplicate scenes from being added based on their scene id

**Removed** ([8c6c6203](https://github.com/red-dragon65/SuprSeed/commit/8c6c6203))

- cleaned up `BaseScene` constructor requirements
  - `Context` is no longer held as a class variable and is not an arg
  - a `parentScene` is no longer held as a class variable and is not an arg
- 🚨 _the client should register items to scenes using the `registerSprite()` or `registerScene` methods rather than relying on these items registering themselves_
- 🚨 _the client should pass `Context` as an arg where they need in their scenes as scenes no longer have a `getContext()` method_

♻️ **Changed** ([8c6c6203](https://github.com/red-dragon65/SuprSeed/commit/8c6c6203))

- **Removed** usage of `SceneChangeStrategy` interface
- renamed `SceneSoftChange.changeScene()` to `SceneSoftChange.changeActiveScene()`
- fixed an issue where `SceneSoftChange` was clearing out the systems logic register
- implemented `SceneHardChange` to actually initialize new scenes on a background thread
- updated `BaseScene` to allow logic to occur after a scene gets instantiated, or before a scene gets destroyed
- 🚨 _the client can now use `SceneHardChange` to load new scenes on a background thread, and show a loading screen in the meantime_

♻️ **Changed** ([a9b98dc6](https://github.com/red-dragon65/SuprSeed/commit/a9b98dc6))

- some interfaces were moved from `...Lib.Images` to `...Core.SpriteObjects.SpriteBase`

♻️ **Changed** ([a9b98dc6](https://github.com/red-dragon65/SuprSeed/commit/a9b98dc6))

- the asset creation pipeline has been changed to work around the new `AnimationPlayer` class
- `SafeAssetLoader` is now `UncheckedAssetBundler`
- `BasicAssetLoader` is now `SafeAssetBundler`
- Asset loaders now return a `SpriteAssetBundle` object
- `SpriteAssetBundle` objects contain `SpriteAsset` objects
- `SpriteAsset` objects hold the sprite image as well as optional sprite `AnimaitonPlayer` object
- 🚨 _for animation control, the client can create a new `AnimationPlayer` via the new `SpriteAsset` wrapper class when retrieving asset bundles_

♻️ **Changed** ([a9b98dc6](https://github.com/red-dragon65/SuprSeed/commit/a9b98dc6))

- **Removed** the `BitmapAnimation` class
- added the `AnimationPlayer` which controls how images are animated
- 🚨 _the client should replace all usages of `BitmapAnimation` with `BitmapCollection`_

♻️ **Changed** ([b10ed994](https://github.com/red-dragon65/SuprSeed/commit/b10ed994))

- the `images` variable has been renamed to `assetRegistry`
- 🚨 _the client should update their asset loading classes to use the new variable name_

✨ **Added** ([ccb00603](https://github.com/red-dragon65/SuprSeed/commit/ccb00603))

- the engine now shows a default icon and name when used as a library in another project
- 🚨 _the client should override the icon and name in their projects Android Manifest as needed_
- 🚨 _the client should NOT specify any theme in their Android Manifest as the engine already provides a default theme_

♻️ **Changed** ([d786acd7](https://github.com/red-dragon65/SuprSeed/commit/d786acd7))

- all singletons are no longer singletons and have now moved to service locator singleton classes
  - this provides a more streamlined way to see which items are being accessed globally
  - allows service instances to be swapped out to a custom instance if needed
- `EngineContext` holds cross cutting concern object that are mostly used internally by the engine
- `EngineTools` holds cross cutting concern object that are likely to be used mostly client side
- 🚨 _the client should update all singleton object retrievals to now go through the `EngineTools` or `EngineContext` classes_

🛠️ **Fixed** ([05dacb1e](https://github.com/red-dragon65/SuprSeed/commit/05dacb1e))

- update `PixelPerfectCollisionDetection` to scale given `ViewPort` coordinates to the screen before doing calculations
- improved `PixelPerfectCollisionDetection` algorithm
  - pixel overlap is now more accurately calculated

♻️ **Changed** ([a7294548](https://github.com/red-dragon65/SuprSeed/commit/a7294548), [05dacb1e](https://github.com/red-dragon65/SuprSeed/commit/05dacb1e))

- the `CanvasData` class has been split into `Screen` and `ViewPort` classes
- `Screen` now represents just the actual devices screen values
- `ViewPort` represents the internal coordinate of the canvas the client will use
- updated internal usage of `CanvasData` to now use `ViewPort` scaling
- updated `Sprite` to return the sprites current width and height
- 🚨 _the client should replace `CanvasData` with the `ViewPort` object when working with screen width and height_
- 🚨 _the client can now use `getWidth()` and `getHeight()` when working with sprites rather than having to first call `getAssetBundle().getSelectedImageSet()`_

✨ **Added** ([95a6044d](https://github.com/red-dragon65/SuprSeed/commit/95a6044d))

- the `SoundMixer` now has more options for controlling sound effects play back
  - play, pause, stop, resume, pauseAll, stopAll, resumeAll

✨ **Added** ([ab6b188f](https://github.com/red-dragon65/SuprSeed/commit/ab6b188f))

- the render system now scales animations based on the devices given refresh rate
- **Removed** the `GlobalFrameStepper` class
- 🚨 _the client can now specify the target frame time animations fit in by calling the `setTargetFrameTime()` method of the `RenderSystem`_

🛠️ **Fixed** ([084c0b97](https://github.com/red-dragon65/SuprSeed/commit/084c0b97))

- fixed an issue where the `EngineConfigurator` could create mismatched dependencies

✨ **Added** ([2e97b3c9](https://github.com/red-dragon65/SuprSeed/commit/2e97b3c9), [a98ac190](https://github.com/red-dragon65/SuprSeed/commit/a98ac190))

- the engine now supports various refresh rates instead of just 60hz
- the engine can now scale logic rates to match the devices refresh rate if it differs from the clients configuration
- 🚨 _the client should update their `EngineConfigurator` to set the `LoopConfig` settings they want to use_

🛠️ **Fixed** ([a98ac190](https://github.com/red-dragon65/SuprSeed/commit/a98ac190), [17d049c9](https://github.com/red-dragon65/SuprSeed/commit/17d049c9))

- replaced `LocationTemporalScaler` with `VelocityScaler` which actually scales physics movement across variable game logic rates
- 🚨 _client should use `applyXVel()` and `applyYVel()` from sprite in order to dynamically scale sprite movement across different logic rates_
- 🚨 _client should reference the `VelocityScaler` value anywhere velocity calculations are occurring in order to ensure movement scales with game logic speeds_

🛠️ **Fixed** ([6b8a6e48](https://github.com/red-dragon65/SuprSeed/commit/6b8a6e48))

- fixed an issue where scenes were not sorting based on layer depth before rendering

🛠️ **Fixed** ([9e45a6b2](https://github.com/red-dragon65/SuprSeed/commit/9e45a6b2))

- created a new `LayerData` to streamline how layer data is accessed and used
  - this fixes a runtime issue where changing the layer depth of an object did not cause items to be sorted
- added a `LayerSync` class to more clearly handle sorting `LayerData` lists
- **Removed** the `setLayerDepth()` from the `Sprite` class
- 🚨 _the client should update usages of `getLayerDepth()` to return a `LayerData` object instead of an integer_
- 🚨 _the client should now set the layer depth via the sprites constructor if `setLayerDepth()` was previously being used, or get the layer depth object from the sprite and set its value if needed_

♻️ **Changed** ([0fe1cf52](https://github.com/red-dragon65/SuprSeed/commit/0fe1cf52))

- the `EngineConfigurator` now takes a `Context` object rather than a `SceneStarter` object
- 🚨 _the client should pass context as required instead of SceneStarter_

♻️ **Changed** ([ce134643](https://github.com/red-dragon65/SuprSeed/commit/ce134643))

- the `SceneStarter` class has been moved to `Core.MainView.GameProcessor.Loop`
- 🚨 _the client should update their imports to match this package change_

✨ **Added** ([b4872a9d](https://github.com/red-dragon65/SuprSeed/commit/b4872a9d))

- a main scene can now be instantiated in the clients `MainActivity` directly by using the new `EngineActivity` `initStartingState()` method
  - this avoids the need to create a `RootScene` class on the client side
- 🚨 _the client should instantiate their scene in their extended EngineActivity class rather than in their extended EngineConfigurator class_

♻️ **Changed** ([b9b0c625](https://github.com/red-dragon65/SuprSeed/commit/b9b0c625))

- various classes have been renamed to follow the "registry" keyword convention
  - the "holder" keyword has been renamed to "registry" for `Lib.Input.Registers` classes
  - the "register" keyword has been renamed to "registry" for `Core.System.Registers` classes
  - the `SubSceneRegister` class has been renamed to `SubSceneRegistry`
- 🚨 _the client should update usages of these packages if they are used_

✨ **Added** ([7f6cc584](https://github.com/red-dragon65/SuprSeed/commit/7f6cc584))

- `TouchTypes` now explicitly outlines the touch events the user can listen for
- 🚨 _the client should use strings retrieved from `TouchTypes` rather than directly hard coding strings when checking processed input action strings_

♻️ **Changed** ([596dcde3](https://github.com/red-dragon65/SuprSeed/commit/596dcde3))

- the `Lib.Input.TouchInput` package has been split into `Lib.Input.Dispatchers` and `Lib.Input.Registers`
- `InputManager` has been renamed to `CentralInputManager`
- the `InputProcessor` interface has been renamed to `EventDispatcher` and placed into `Lib.Input.Dispatchers`
- `InputProcessorRegister` has been renamed to `InputDispatchHolder` and placed int `Lib.Input.Registers`
- 🚨 _the client should replace usages of `Lib.Input.InputManager` with `Lib.CentralInputManager`_
- 🚨 _the client should update imports in order to reflect package changes regarding `Lib.Input` if those classes were used_

♻️ **Changed** ([0f191860](https://github.com/red-dragon65/SuprSeed/commit/0f191860))

- `AssetLoader` has been split into `BasicAssetLoader` and `SafeAssetLoader`
- `SafeAssetLoader` returns a place holder image if the requested image cannot be returned instead of throwing an exception
- `BasicAssetLoader` requires the client to handle optional return types if the requested image cannot be returned instead of throwing an exception
- **Note: these changes are further affected by (a9b98dc6)**
- ~~_client should now update all uses of `AssetLoader` to either the `BasicAssetLoader` or `SafeAssetLoader`_~~
- ~~_client should specify a placeholder image for their asset loader when using the `SafeAssetLoader` class_~~

✨ **Added** ([0f191860](https://github.com/red-dragon65/SuprSeed/commit/0f191860))

- the CentralLogger can now optionally print the stack trace of logged messages

✨ **Added** ([15dc9870](https://github.com/red-dragon65/SuprSeed/commit/15dc9870))

- default valid fps values can now be reference from the `Lib.Images` package to avoid using an invalid fps when creating animated bitmap assets

♻️ **Changed** ([15dc9870](https://github.com/red-dragon65/SuprSeed/commit/15dc9870))

- replaced the `CatLogger` logging implementation with an `AndroidCatLogger` implementation that uses android specific log levels when engine logs are written
- ~~BitmapAnimation now throws and catches a custom exception if an invalid fps is provided in the construcor~~ **This has been replaced by the animation player (a9b98dc6)**
- ~~BitmapAnimation now finds the closest valid fps if the given fps provided is invalid~~ **This is now handled by the animation player (a9b98dc6)**
  - 🚨 _client should reference `DefaultFps` under `Lib.Images` in order to avoid running into unsupported animation fps values_

✨ **Added** ([8542d937](https://github.com/red-dragon65/SuprSeed/commit/8542d937), [9c8a2904](https://github.com/red-dragon65/SuprSeed/commit/9c8a2904))

- created an architecture diagram to show how the Core package works

✨ **Added** ([00c14c08](https://github.com/red-dragon65/SuprSeed/commit/00c14c08))

- ~~collision diagnostic overlay implementation can now be specified in the client engine configuration~~ **Client can specify diagnoser in the EngineTools service locator (d786acd7)**
- updated sprite to now allow the user to specify whether the sprite is included in the collision diagnostic overlay

🛠️ **Fixed** ([00c14c08](https://github.com/red-dragon65/SuprSeed/commit/00c14c08))

- fixed an issue where collision diagnostic overlays were not showing for some sprites

♻️ **Changed** ([f3270255](https://github.com/red-dragon65/SuprSeed/commit/f3270255))

- the input package has been moved from `Core` to `Lib`
- `InputHandler` interface has moved from `...Core.InputHander.TouchInput` to `Core.MainView.GameProcessor.Loop`
- 🚨 _client should update imports related to:_ - `...Engine.Core.InputHandler...` should now be `...Engine.Lib.Input...`

🛠️ **Fixed** ([a38ca0a3](https://github.com/red-dragon65/SuprSeed/commit/a38ca0a3))

- ~~changed `Context` variable to no longer be static in scene related classes~~ **Context was removed as a dependency of scenes (8c6c6203)**

♻️ **Changed** ([e8006c62](https://github.com/red-dragon65/SuprSeed/commit/e8006c62))

- moved `Core.System` interfaces into a sub `Registerables` package
- 🚨 _client should update imports to use `Core.System.Registerables.` rather than `Core.System.` for `Layerable`, `LogicRunnable`, `Renderable`, `RenderableAndLayerable`_
