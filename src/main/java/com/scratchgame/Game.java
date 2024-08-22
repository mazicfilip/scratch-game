package com.scratchgame;

import com.scratchgame.config.GameConfig;
import com.scratchgame.config.WinningCombination;
import com.scratchgame.enums.SymbolType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private Matrix matrix;
    private double betAmount;
    private GameConfig config;
    private Map<String, Symbol> symbolMap;

    public Game(int rows, int cols, double betAmount, GameConfig config) {
        this.betAmount = betAmount;
        this.config = config;
        this.symbolMap = config.getSymbols();
        this.matrix = new Matrix(rows, cols, symbolMap);
    }

    public void play() {
        matrix.generateMatrix(config);
        matrix.printMatrix();
        evaluateWinningCombinations();
    }

    public Matrix getMatrix() {
        return matrix;
    }

    private void evaluateWinningCombinations() {
        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();
        double totalReward = 0;

        for (String symbol : symbolMap.keySet()) {
            Symbol symbolObj = symbolMap.get(symbol);
            if (symbolObj.getType() != SymbolType.STANDARD) {
                continue;
            }

            Symbol[][] grid = matrix.getGrid();
            int count = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j].getName().equals(symbol)) {
                        count++;
                    }
                }
            }

            for (WinningCombination combination : config.getWinCombinations().values()) {
                if (combination.getCount() == count && combination.getWhenCondition().equals("same_symbols")) {
                    double reward = betAmount * symbolMap.get(symbol).getRewardMultiplier() * combination.getRewardMultiplier();
                    totalReward += reward;
                    appliedWinningCombinations.computeIfAbsent(symbol, k -> new ArrayList<>()).add(combination.getGroup());
                }
            }

            for (WinningCombination combination : config.getWinCombinations().values()) {
                if (combination.getWhenCondition().equals("linear_symbols")) {
                    boolean match = checkLinearCombination(combination, grid, symbol);
                    if (match) {
                        double reward = betAmount * symbolMap.get(symbol).getRewardMultiplier() * combination.getRewardMultiplier();
                        totalReward += reward;
                        appliedWinningCombinations.computeIfAbsent(symbol, k -> new ArrayList<>()).add(combination.getGroup());
                    }
                }
            }
        }

        totalReward = applyBonusSymbols(totalReward, appliedWinningCombinations);

        System.out.println("Total Reward: " + totalReward);
    }

    private boolean checkLinearCombination(WinningCombination combination, Symbol[][] grid, String symbol) {
        for (List<String> area : combination.getCoveredAreas()) {
            boolean match = true;
            for (String position : area) {
                String[] indices = position.split(":");
                int row = Integer.parseInt(indices[0]);
                int col = Integer.parseInt(indices[1]);
                if (!grid[row][col].getName().equals(symbol)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }

    private double applyBonusSymbols(double totalReward, Map<String, List<String>> appliedWinningCombinations) {
        Symbol[][] grid = matrix.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Symbol symbol = grid[i][j];
                if (symbol.getType() == SymbolType.BONUS && !appliedWinningCombinations.isEmpty()) {
                    switch (symbol.getImpact()) {
                        case MULTIPLY_REWARD:
                            totalReward *= symbol.getRewardMultiplier();
                            break;
                        case EXTRA_BONUS:
                            totalReward += symbol.getExtra();
                            break;
                        case MISS:
                            break;
                    }
                }
            }
        }
        return totalReward;
    }
}
