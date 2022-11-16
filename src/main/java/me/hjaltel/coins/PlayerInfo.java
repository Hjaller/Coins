package me.hjaltel.coins;

import java.util.UUID;

public class PlayerInfo {
    private UUID uuid;
    private String playername;

    private int coins;

    public PlayerInfo(UUID uuid, String playername, int coins) {
        this.uuid = uuid;
        this.playername = playername;
        this.coins = coins;
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


}
