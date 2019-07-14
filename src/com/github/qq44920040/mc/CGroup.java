package com.github.qq44920040.mc;

import com.github.qq44920040.mc.Entity.GroupCmd;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.*;

public class CGroup extends JavaPlugin {
   private Map<String,String> Permissionkv = new HashMap<>();
    private Map<String,List<GroupCmd>> GroupCmdinfo = new HashMap<>();
    public static CGroup cGroup;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        ReloadConfig();
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("startcommand")){
            if (args.length==2){
                System.out.println("初步");
                System.out.println(Permissionkv.containsKey(args[0]));
                System.out.println(GroupCmdinfo.containsKey(args[0]));
                if (Permissionkv.containsKey(args[0])&&GroupCmdinfo.containsKey(args[0])){
                    System.out.println("進入");
                    Player sender1 = (Player) sender;
                    if (sender.hasPermission(Permissionkv.get(args[0]))){
                        System.out.println("有權限");
                        List<GroupCmd> groupCmds = GroupCmdinfo.get(args[0]);
                        for (GroupCmd cmd:groupCmds){
                            String cmd1 = cmd.getCmd().replace("%player%",sender.getName()).replace("%count%",args[1]);
                            double delay = cmd.getDelay();
                            String msg = cmd.getMsg();
                            boolean ignorePermission = cmd.isIgnorePermission();
                            boolean playerl = cmd.isPlayerl();
                            new ThreadBukkit(sender1,cmd1,delay,ignorePermission,playerl,msg,this).start();
                        }
                    }
                }
            }else if(args.length==1&&args[0].equalsIgnoreCase("reload")&&sender.isOp()){
                Permissionkv.clear();
                GroupCmdinfo.clear();
                ReloadConfig();
                sender.sendMessage("§e§l重载插件成功");
            }
        }
        return super.onCommand(sender, command, label, args);
    }

//    private void CmdDelay(final OfflinePlayer player,final String cmd, double delaytime,final boolean ignorePermission, final boolean isplayer,final String msg){
//        int dellay = (int)delaytime;
//        OfflinePlayer player1 = player;
//        new BukkitRunnable(){
//            @Override
//            public void run() {
//                System.out.println("進入執行");
//                if (player.isOnline()){
//                    Player player1 = player.getPlayer();
//                    if (isplayer){
//                        if (ignorePermission){
//                            player1.setOp(true);
//                            player1.chat(cmd);
//                            player1.setOp(false);
//                            player1.sendMessage(msg);
//                        }else {
//                            player1.chat(cmd);
//                            player1.sendMessage(msg);
//                        }
//                    }else {
//                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),cmd);
//                    }
//                }
//            }
//        }.runTaskLater(this,20L*dellay);
//    }

    private void ReloadConfig() {
        reloadConfig();
        Set<String> mines = getConfig().getConfigurationSection("CGroup").getKeys(false);
        for (String temp : mines) {
            Set<String> innertemp = getConfig().getConfigurationSection("CGroup."+temp).getKeys(false);
            List<GroupCmd> innerGroup = new ArrayList<>();
            for (String intemp:innertemp){
                String cmd = getConfig().getString("CGroup." + temp + "." + intemp+".cmd");
                boolean ignorePermission = getConfig().getBoolean("CGroup." + temp + "." + intemp+".ignorePermission");
                double Delay = getConfig().getDouble("CGroup." + temp + "." + intemp+".Delay");
                boolean IsPlayer = getConfig().getBoolean("CGroup." + temp + "." + intemp+".IsPlayer");
                String Msg = getConfig().getString("CGroup." + temp + "." + intemp+".Msg");
                System.out.println(new GroupCmd(cmd,ignorePermission,Delay,IsPlayer,Msg).toString());
                innerGroup.add(new GroupCmd(cmd,ignorePermission,Delay,IsPlayer,Msg));
            }
            GroupCmdinfo.put(temp,innerGroup);
        }

        Set<String> permissionmines = getConfig().getConfigurationSection("PermissionCGroup").getKeys(false);
        for (String permissionGroup:permissionmines){
            System.out.println(getConfig().getString("PermissionCGroup."+permissionGroup));
            Permissionkv.put(permissionGroup,getConfig().getString("PermissionCGroup."+permissionGroup));
        }
    }


}
