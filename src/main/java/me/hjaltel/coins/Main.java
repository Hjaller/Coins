package me.hjaltel.coins;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private static Main instance;
    private Connection connection;
    public String host, database, username, password;
    public int port;
    public Events events;
    public Sql sql;



    public HashMap<UUID, PlayerInfo> playerInfo = new HashMap<>();


    @Override
    public void onEnable() {
        this.events = new Events();
        this.sql = new Sql();

        loadConfig();
        mysqlSetup();
        instance = this;

        Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);

        Sql.createTables();
    }

    @Override
    public void onDisable() {
        try {
            this.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Main getInstance() {
        return instance;
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    public void configReload(){
        reloadConfig();

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void mysqlSetup() {
        host = this.getConfig().getString("MySQL.host");
        port = this.getConfig().getInt("MySQL.port");
        database = this.getConfig().getString("MySQL.database");
        username = this.getConfig().getString("MySQL.username");
        password = this.getConfig().getString("MySQL.password");

        try {

            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                setConnection(
                        DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true&characterEncoding=utf8",
                                this.username, this.password));

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
