package com.scratchgame;

import com.scratchgame.config.GameConfig;
import com.scratchgame.config.WinningCombination;
import com.scratchgame.enums.SymbolType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class GameTest {
    private GameConfig config;
    private Game game;

    @Before
    public void setUp() throws IOException {
        String configFilePath = "src/main/resources/config.json";
        config = ConfigLoader.loadConfig(configFilePath);

        game = new Game(config.getRows(), config.getColumns(), 100, config);
    }

    @Test
    public void testGameInitialization() {
        assertNotNull("Config should not be null", config);
        assertEquals("Number of rows should match the config", config.getRows(), 3);
        assertEquals("Number of columns should match the config", config.getColumns(), 3);
    }

    @Test
    public void testMatrixGeneration() {
        game.play();

        Symbol[][] matrix = game.getMatrix().getGrid();
        assertNotNull("Matrix should not be null", matrix);
        assertEquals("Matrix should have correct number of rows", 3, matrix.length);
        assertEquals("Matrix should have correct number of columns", 3, matrix[0].length);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                assertNotNull("Symbol should be valid and present in the symbol map", matrix[i][j]);
                assertTrue("Symbol name should be present in config",
                        config.getSymbols().containsKey(matrix[i][j].getName()));
            }
        }
    }

    @Test
    public void testWinningCombinations() {
        game.play();

        Map<String, WinningCombination> winCombinations = config.getWinCombinations();
        for (WinningCombination combination : winCombinations.values()) {
            assertNotNull("Winning combination should be present", combination);
            assertTrue("Reward multiplier should be greater than 0", combination.getRewardMultiplier() > 0);
        }
    }

    @Test
    public void testBonusSymbolApplication() {
        game.play();

        Map<String, Symbol> symbols = config.getSymbols();
        for (Symbol symbol : symbols.values()) {
            if (symbol.getType() == SymbolType.BONUS) {
                assertNotNull("Bonus symbol should have an impact type", symbol.getImpact());
            }
        }
    }
}
