package me.hjaltel.coins;

import java.util.UUID;

public class PlayerInfo {
    private UUID uuid;
    private String playername;

    private int coins, spent, got, gotPayed, payed;

    public PlayerInfo(UUID uuid, String playername, int coins, int spent, int got, int gotPayed, int payed) {
        this.uuid = uuid;
        this.playername = playername;
        this.coins = coins;
        this.spent = spent;
        this.got = got;
        this.gotPayed = gotPayed;
        this.payed = payed;
    }

    public UUID getUUID() {
        return this.getUUID();
    }

    public String getPlayername() {
        return this.playername;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getSpent() {
        return this.spent;
    }

    public int getGot() {
        return this.got;
    }

    public int getGotPayed() {
        return this.gotPayed;
    }

    public int getPayed() {
        return this.payed;
    }


}
