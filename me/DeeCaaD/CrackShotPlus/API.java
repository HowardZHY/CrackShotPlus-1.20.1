package me.DeeCaaD.CrackShotPlus;

import me.DeeCaaD.CrackShotPlus.Packets.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class API {
    public static double version;
    static SetupVersion sv;
    static Plugin plugin;

    public API() {
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static SetupVersion getNMS() {
        return sv;
    }

    public static double getVersion() {
        return version;
    }

    static boolean setupVer() {
        String ver;
        try {
            ver = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
            System.out.println("[CSP] Server Version :" + ver);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        switch (ver) {
            case "v1_20_R1":
                try {
                    sv = new SetupVersion_1_20_R1();
                    version = 1.201;
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                break;
            case "v1_19_R3":
                try {
                    sv = new SetupVersion_1_19_R2();
                    version = 1.193;
                } catch (Exception exc) {
                    System.out.println("[CSP] You have to use a bytecode editor to edit or remap SetupVersion_1_19_R2 to make it working on 1.19.4");
                    System.out.println(exc);
                }
                break;
            case "v1_19_R2":
                sv = new SetupVersion_1_19_R2();
                version = 1.192;
                break;
            case "v1_19_R1":
                sv = new SetupVersion_1_19_R1();
                version = 1.191;
                break;
            case "v1_18_R2":
                sv = new SetupVersion_1_18_R2();
                version = 1.182;
                break;
            case "v1_18_R1":
                sv = new SetupVersion_1_18_R1();
                version = 1.181;
                break;
            case "v1_16_R3":
                sv = new SetupVersion_1_16_R3();
                version = 1.163;
                break;
            case "v1_16_R2":
                sv = new SetupVersion_1_16_R2();
                version = 1.162;
                break;
            case "v1_16_R1":
                sv = new SetupVersion_1_16_R1();
                version = 1.161;
                break;
            case "v1_15_R1":
                sv = new SetupVersion_1_15_R1();
                version = 1.151;
                break;
            case "v1_14_R1":
                sv = new SetupVersion_1_14_R1();
                version = 1.141;
                break;
            case "v1_13_R2":
                sv = new SetupVersion_1_13_R2();
                version = 1.132;
                break;
            case "v1_13_R1":
                sv = new SetupVersion_1_13_R1();
                version = 1.131;
                break;
            case "v1_12_R1":
                sv = new SetupVersion_1_12_R1();
                version = 1.121;
                break;
            case "v1_11_R1":
                sv = new SetupVersion_1_11_R1();
                version = 1.111;
                break;
            case "v1_10_R1":
                sv = new SetupVersion_1_10_R1();
                version = 1.101;
                break;
            case "v1_9_R2":
                sv = new SetupVersion_1_9_R2();
                version = 1.092;
                break;
            case "v1_9_R1":
                sv = new SetupVersion_1_9_R1();
                version = 1.091;
                break;
            case "v1_8_R3":
                sv = new SetupVersion_1_8_R3();
                version = 1.083;
        }

        return sv != null;
    }

    static boolean checkCrackShot() {
        Plugin ver = Bukkit.getServer().getPluginManager().getPlugin("CrackShot");
        return ver != null;
    }

    public static String changeVariables(Player p, String s) {
        return s;
    }
}