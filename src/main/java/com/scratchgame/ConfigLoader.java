package com.scratchgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scratchgame.config.GameConfig;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {
    public static GameConfig loadConfig(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        GameConfig config = mapper.readValue(new File(filePath), GameConfig.class);

        Map<String, Symbol> symbolMap = new HashMap<>();
        for (Map.Entry<String, Symbol> entry : config.getSymbols().entrySet()) {
            Symbol symbol = entry.getValue();
            symbol.setName(entry.getKey());
            symbolMap.put(entry.getKey(), symbol);
        }

        config.setSymbols(symbolMap);
        return config;
    }
}
