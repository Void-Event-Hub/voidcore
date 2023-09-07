# Void Core
A Minecraft mod developed for the **Void Event Hub** to interact with their discord.

## Features

### Cosmetics
Players can equip cosmetics using the cosmetics menu, which will be displayed instead of their armor.

#### Current cosmetics
* Knight Armor

### Data Persistence
Valuable data like when a player joins or leaves the server is stored in a database.

#### Current data
* Player join/leave time
* All data that is stored on the database, like a players discord info in the void event hub server.

### Teams
You can use `/team <teamname> setspawn` to set the spawn of the team.
Players get automatically assigned to a team whenever `/startevent` has been used. Players will also get teleported
to the team spawn.

### Death Ban
Players will be banned when they die more than a configurable amount of times.
Setting this value to `0` means that the player has 1 life.

## Dependencies
* [Cardinal Components API](https://github.com/Ladysnake/Cardinal-Components-API)
* [owo-lib](https://github.com/wisp-forest/owo-lib/)
* [geckolib](https://github.com/bernie-g/geckolib)
* [MongoDB driver](https://github.com/mongodb/mongo-java-driver) (Automatically included by gradle)


