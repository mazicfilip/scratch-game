package com.scratchgame;

import com.scratchgame.config.GameConfig;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String configFilePath = "src/main/resources/config.json";

        try {
            GameConfig config = ConfigLoader.loadConfig(configFilePath);

            Game game = new Game(config.getRows(), config.getColumns(), 100, config);

            game.play();

        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
        }
    }
}