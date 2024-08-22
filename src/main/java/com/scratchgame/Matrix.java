package com.scratchgame;

import com.scratchgame.config.GameConfig;
import com.scratchgame.config.StandardSymbolProbability;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Matrix {
    private Symbol[][] grid;
    private int rows;
    private int cols;
    private Map<String, Symbol> symbolMap;

    public Matrix(int rows, int cols, Map<String, Symbol> symbolMap) {
        this.rows = rows;
        this.cols = cols;
        grid = new Symbol[rows][cols];
        this.symbolMap = symbolMap;
    }

    public void generateMatrix(GameConfig config) {
        Random random = new Random();
        List<StandardSymbolProbability> standardSymbols = config.getProbabilities().getStandardSymbols();
        Map<String, Integer> bonusProbabilities = config.getProbabilities().getBonusSymbols().getSymbols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;
                Symbol symbolToPlace;
                if (index < standardSymbols.size()) {
                    Map<String, Integer> probabilities = standardSymbols.get(index).getSymbols();
                    grid[i][j] = getRandomSymbol(probabilities, random);
                }
            }
        }

        addBonusSymbols(bonusProbabilities, random);
    }

    private Symbol getRandomSymbol(Map<String, Integer> probabilities, Random random) {
        int totalProbability = probabilities.values().stream().mapToInt(Integer::intValue).sum();
        int randValue = random.nextInt(totalProbability);
        int cumulativeProbability = 0;

        for (Map.Entry<String, Integer> entry : probabilities.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (randValue < cumulativeProbability) {
                return symbolMap.get(entry.getKey());
            }
        }

        return symbolMap.values().iterator().next();
    }

    private void addBonusSymbols(Map<String, Integer> bonusProbabilities, Random random) {
        double bonusChance = 0.2;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (random.nextDouble() < bonusChance) {
                    grid[i][j] = getRandomSymbol(bonusProbabilities, random);
                }
            }
        }
    }

    public Symbol[][] getGrid() {
        return grid;
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].getName() + " ");
            }
            System.out.println();
        }
    }
}
