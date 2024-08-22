package com.scratchgame;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scratchgame.enums.ImpactType;
import com.scratchgame.enums.SymbolType;

public class Symbol {
    private String name;

    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;
    private SymbolType type;
    private double extra;
    private ImpactType  impact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public SymbolType getType() {
        return type;
    }

    public void setType(SymbolType symbolType) {
        this.type = symbolType;
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

    public ImpactType getImpact() {
        return impact;
    }

    public void setImpact(ImpactType impact) {
        this.impact = impact;
    }
}
