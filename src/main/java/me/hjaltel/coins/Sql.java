package me.hjaltel.coins;

import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql {

    public static void createTables() {
        new BukkitRunnable() {

            @Override
            public void run() {
                try {
                    PreparedStatement logsTable = Main.getInstance().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `vagt_logs` (`id` int(11) DEFAULT 0, `uuid` varchar(255) DEFAULT NULL,`log` varchar(255) DEFAULT NULL, `time` varchar(255) DEFAULT NULL)");
                    logsTable.executeUpdate();

                    PreparedStatement playerTable = Main.getInstance().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `players` (`uuid` varchar (255) DEFAULT NULL, `username` varchar(255) DEFAULT NULL, `hiredAt` varchar(255) DEFAULT NULL, `lastJoin` varchar(255) DEFAULT NULL, `ontime` int(11) DEFAULT 0, `vagtRank` varchar(255) DEFAULT NULL, `warnings` int(11) DEFAULT 0, `level` int(11) DEFAULT 0, `kills` int(11) DEFAULT 0, `deaths` int(11) DEFAULT 0, `commands` int(11) DEFAULT 0, `chat` int(11) DEFAULT 0, `legalItems` int(11) DEFAULT 0, `jails` int(11) DEFAULT 0, `warp_c` int(11) DEFAULT 0, `warp_b` int(11) DEFAULT 0, `warp_a` int(11) DEFAULT 0, PRIMARY KEY (`uuid`))");
                    playerTable.executeUpdate();



                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }
}
