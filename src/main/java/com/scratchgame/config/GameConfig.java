package com.scratchgame.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scratchgame.Symbol;

import java.util.Map;

public class GameConfig {
    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private ProbabilitiesConfig probabilities;
    @JsonProperty("win_combinations")
    private Map<String, WinningCombination> winCombinations;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Map<String, WinningCombination> getWinCombinations() {
        return winCombinations;
    }

    public void setWinCombinations(Map<String, WinningCombination> winCombinations) {
        this.winCombinations = winCombinations;
    }

    public ProbabilitiesConfig getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(ProbabilitiesConfig probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
