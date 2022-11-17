package me.hjaltel.coins;

import me.hjaltel.coins.Main;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class Sql {

    public static void createTables() {
        new BukkitRunnable() {

            @Override
            public void run() {
                try {
                    PreparedStatement logsTable = Main.getInstance().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `coins_logs` (`id` int(11) DEFAULT 0, `uuid` varchar(255) DEFAULT NULL,`log` varchar(255) DEFAULT NULL, `time` varchar(255) DEFAULT NULL)");
                    logsTable.executeUpdate();

                    PreparedStatement playerTable = Main.getInstance().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `players` (`uuid` varchar (255) DEFAULT NULL, `username` varchar(255) DEFAULT NULL, `coins` int(11) DEFAULT 0, `spent` varchar(255) DEFAULT NULL, `got` int(11) DEFAULT 0, `gotPayed` int(11) DEFAULT 0, `payed` int(11) DEFAULT 0, PRIMARY KEY (`uuid`))");
                    playerTable.executeUpdate();



                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }


    public static int getInt(UUID uuid, String table) {
        try {
            PreparedStatement statement = Main.getInstance().getConnection().prepareStatement("SELECT * from `players` WHERE `uuid`=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            while(results.next()) {
                return results.getInt(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public static String getString(UUID uuid, String table) {
        try {
            PreparedStatement statement = Main.getInstance().getConnection().prepareStatement("SELECT * from `players` WHERE `uuid`=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            while(results.next()) {
                return results.getString(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Boolean playerExist(UUID uuid) {
        try {
            PreparedStatement statement = Main.getInstance().getConnection().prepareStatement("SELECT * from `players` WHERE `uuid`=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            while(results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(UUID uuid) {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(!playerExist(uuid)) {
                    try {
                        PreparedStatement statement = Main.getInstance().getConnection().prepareStatement("insert into `players` (`uuid`, `username`, `coins`, `spent`, `got`, `gotPayed`, `payed`) VALUES (?,?,?,?,?,?,?)");
                        statement.setString(1, uuid.toString());
                        statement.setString(2, Bukkit.getPlayer(uuid).getName());
                        statement.setInt(3, 0);
                        statement.setInt(4, 0);
                        statement.setInt(5, 0);
                        statement.setInt(6, 0);
                        statement.setInt(7, 0);




                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }  else{
                    return;
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }
}
