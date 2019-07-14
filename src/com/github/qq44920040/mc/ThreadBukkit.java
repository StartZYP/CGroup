package com.github.qq44920040.mc;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ThreadBukkit extends BukkitRunnable {
    private OfflinePlayer player;
    private String cmd;
    private int delaytime;
    private boolean ignorePermission;
    private boolean isplayer;
    private String msg;
    private CGroup plugin;

    public ThreadBukkit(OfflinePlayer player, String cmd, double delaytime, boolean ignorePermission, boolean isplayer, String msg, CGroup plugin) {
        this.player = player;
        this.cmd = cmd;
        this.delaytime = (int)delaytime;
        this.ignorePermission = ignorePermission;
        this.isplayer = isplayer;
        this.msg = msg;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        System.out.println("進入執行");
        if (player.isOnline()){
            Player player1 = player.getPlayer();
            if (isplayer){
                if (ignorePermission){
                    player1.setOp(true);
                    player1.chat(cmd);
                    player1.setOp(false);
                    player1.sendMessage(msg);
                }else {
                    player1.chat(cmd);
                    player1.sendMessage(msg);
                }
            }else {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),cmd);
            }
        }
    }

    public void start(){
        this.runTaskLater(this.plugin,20L*delaytime);
    }
}
