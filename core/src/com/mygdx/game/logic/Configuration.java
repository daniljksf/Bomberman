package com.mygdx.game.logic;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configuration {
    private String pathToTextureBomberOne = "bomberSpritePlayer1.png";
    private String pathToTextureDeadBomberOne = "bomberSpritePlayer2.png";
    private String pathToTextureBomberTwo = "DeadSpritePlayer1.png";
    private String pathToTextureDeadBomberTwo = "DeadSpritePlayer2.png";
    private String pathToMap = "testMap.txt";

    public static Configuration loadConfigs(String pathToConfigFile) {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(pathToConfigFile));
            Configuration configuration = gson.fromJson(reader, Configuration.class);
            configuration.nullToDefault();
            return configuration;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void nullToDefault() {
        this.pathToTextureBomberOne = (pathToTextureBomberOne == null)? this.pathToTextureBomberOne : pathToTextureBomberOne ;
        this.pathToTextureBomberTwo = (pathToTextureBomberTwo == null)? this.pathToTextureBomberTwo : pathToTextureBomberTwo ;
        this.pathToTextureDeadBomberOne = (pathToTextureDeadBomberOne == null)? this.pathToTextureDeadBomberOne : pathToTextureDeadBomberOne ;
        this.pathToTextureDeadBomberTwo = (pathToTextureDeadBomberTwo == null)? this.pathToTextureDeadBomberTwo : pathToTextureDeadBomberTwo ;
        this.pathToMap = (pathToMap == null)? this.pathToMap : pathToMap;
    }

    public String getPathToTextureBomberOne() {
        return pathToTextureBomberOne;
    }

    public String getPathToTextureDeadBomberOne() {
        return pathToTextureDeadBomberOne;
    }

    public String getPathToTextureBomberTwo() {
        return pathToTextureBomberTwo;
    }

    public String getPathToTextureDeadBomberTwo() {
        return pathToTextureDeadBomberTwo;
    }

    public String getPathToMap() { return pathToMap; }
}
