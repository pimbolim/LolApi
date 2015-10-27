package com.run.donkey.lolapi.ClassesAuxiliares;

/**
 * Created by nuno on 23/10/2015.
 */
public class ChampionDto {

    Boolean active;
    Boolean botEnabled;
    Boolean botMmEnabled;
    Boolean freeToPlay;
    long id;
    Boolean rankedPlayEnabled;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getBotEnabled() {
        return botEnabled;
    }

    public void setBotEnabled(Boolean botEnabled) {
        this.botEnabled = botEnabled;
    }

    public Boolean getBotMmEnabled() {
        return botMmEnabled;
    }

    public void setBotMmEnabled(Boolean botMmEnabled) {
        this.botMmEnabled = botMmEnabled;
    }

    public Boolean getFreeToPlay() {
        return freeToPlay;
    }

    public void setFreeToPlay(Boolean freeToPlay) {
        this.freeToPlay = freeToPlay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getRankedPlayEnabled() {
        return rankedPlayEnabled;
    }

    public void setRankedPlayEnabled(Boolean rankedPlayEnabled) {
        this.rankedPlayEnabled = rankedPlayEnabled;
    }
}
