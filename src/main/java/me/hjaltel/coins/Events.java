package me.hjaltel.coins;

import jdk.nashorn.internal.ir.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        new BukkitRunnable() {

            @Override
            public void run() {
                if (Sql.playerExist(uuid)) {
                    Main.getInstance().playerInfo.put(e.getPlayer().getUniqueId(), new PlayerInfo(e.getPlayer().getUniqueId(), e.getPlayer().getName(), Sql.getInt(uuid, "coins"), Sql.getInt(uuid, "spent"), Sql.getInt(uuid, "got"), Sql.getInt(uuid, "gotPayed"), Sql.getInt(uuid, "payed")));
                } else {
                    Sql.createPlayer(uuid);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

}
