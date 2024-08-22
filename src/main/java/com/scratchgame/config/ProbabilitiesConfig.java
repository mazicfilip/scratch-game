package com.scratchgame.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProbabilitiesConfig {
    @JsonProperty("standard_symbols")
    List<StandardSymbolProbability> standardSymbols;
    @JsonProperty("bonus_symbols")
    BonusSymbolsConfig bonusSymbols;

    public List<StandardSymbolProbability> getStandardSymbols() {
        return standardSymbols;
    }

    public void setStandardSymbols(List<StandardSymbolProbability> standardSymbols) {
        this.standardSymbols = standardSymbols;
    }

    public BonusSymbolsConfig getBonusSymbols() {
        return bonusSymbols;
    }

    public void setBonusSymbols(BonusSymbolsConfig bonusSymbols) {
        this.bonusSymbols = bonusSymbols;
    }
}
