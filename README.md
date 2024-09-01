# 2D Game
A simple 2D game engine which features custom map loading, written entirely in Java.
# Controls
WASD controls. A/D to move left and right. W to jump, S to slam down.
# Creating Maps
A map is entirely stored in an .xml file. For the provided map, the syntax looks like:
```xml
<Information>
    <MapInformation Name="TestMap1" SpawnX="0" SpawnY="1"/>
    <MapData>
      <Block Type="grass" X="0" Y="0"/>
      <Block Type="grass" X="1" Y="0"/>
      ...
  </MapData>
</Information>
```
## Map Breakdown
**MapInformation** contains the basic information about the map like the name and spawn X and Y location.
**MapData** contains all the blocks within the map.
**A block** contains its type, X, and Y location. New types can be added by putting a square png image in the sprites folder. You can then reference it by setting the type to the name of the image (excluding the .png).
## Loading The Map
You can load the map by calling on the Renderer object within Core.java ``renderer.loadMap(filePath)``, replacing filePath with the path in the maps folder.
# Remarks
This project was just inteded to be a simple 2D engine for player movement and collision. I submitted it as a project (where the project had to have some sort of animation in it), for Applied Advanced Computer Science at Academies of Loudoun. It does not support any way to "complete" the game such as goals or objectives, but the engine was designed to have some modularity. You may encounter some collision bugs such as phasing through blocks or occasionally "snapping" towards a block.
