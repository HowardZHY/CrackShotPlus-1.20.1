package me.DeeCaaD.CrackShotPlus.Packets;

import com.mojang.datafixers.util.Pair;
import me.DeeCaaD.CrackShotPlus.API;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment;
import net.minecraft.network.protocol.game.PacketPlayOutPosition;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.pathfinder.PathEntity;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

@SuppressWarnings("all")
public class SetupVersion_1_20_R1 implements SetupVersion {
    public Set<RelativeMovement> set;
    public SetupVersion_1_20_R1() {
        this.set = new HashSet(Arrays.asList(RelativeMovement.a, RelativeMovement.b, RelativeMovement.c, RelativeMovement.d, RelativeMovement.e));
    }

    public ItemStack getHand(org.bukkit.entity.Player var1) {
        return var1.getInventory().getItemInMainHand();
    }

    public void invisibleProjectile(Entity var1) {
        Iterator var2 = var1.getWorld().getPlayers().iterator();

        while(var2.hasNext()) {
            Player var3 = (Player)var2.next();
            PacketPlayOutEntityDestroy var4 = new PacketPlayOutEntityDestroy(new int[]{var1.getEntityId()});
            ((CraftPlayer)var3).getHandle().c.a(var4);
        }
    }

    public void blockBreakAnimation(Block b, int i) {
        Random r = new Random();
        int j = r.nextInt(1000);
        Location l = b.getLocation();
        Iterator it = l.getWorld().getPlayers().iterator();

        while (it.hasNext()) {
            Player p = (Player)it.next();
            PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(j, new BlockPosition(l.getBlockX(), l.getBlockY(), l.getBlockZ()), i);
            ((CraftPlayer)p).getHandle().c.a(packet);
        }
    }

    public void actionBarMessage(org.bukkit.entity.Player var1, String var2) {
        var2 = API.changeVariables(var1, var2);
        var1.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent[]) TextComponent.fromLegacyText(var2));
    }

    public void sendTitleAndSubtitle(org.bukkit.entity.Player var1, String var2, String var3, int var4, int var5) {
        if (var2 == null && var3 != null) {
            var2 = "";
        }

        if (var2 != null) {
            var2 = API.changeVariables(var1, var2);
        }

        if (var3 != null) {
            var3 = API.changeVariables(var1, var3);
        }

        var1.sendTitle(var2, var3, 5, 20, 5);
    }

    public ItemStack addNBTValue(ItemStack var1, String var2, String var3) {
        ItemMeta var4 = var1.getItemMeta();
        NamespacedKey var5 = new NamespacedKey(API.getPlugin(), var2);
        if (!var4.getPersistentDataContainer().has(var5, PersistentDataType.STRING)) {
            var4.getPersistentDataContainer().set(var5, PersistentDataType.STRING, var3);
        } else {
            String var6 = (String)var4.getPersistentDataContainer().get(var5, PersistentDataType.STRING);
            var4.getPersistentDataContainer().set(var5, PersistentDataType.STRING, var6 + "," + var3);
        }

        var1.setItemMeta(var4);
        return var1;
    }

    public ItemStack removeNBTValue(ItemStack var1, String var2, String var3) {
        ItemMeta var4 = var1.getItemMeta();
        NamespacedKey var5 = new NamespacedKey(API.getPlugin(), var2);
        if (var4.getPersistentDataContainer().has(var5, PersistentDataType.STRING)) {
            String var6 = (String)var4.getPersistentDataContainer().get(var5, PersistentDataType.STRING);
            if (var6.contains(var3)) {
                String var7 = var6;
                if (var6.contains(var3 + ",") && !var6.equalsIgnoreCase(var3)) {
                    var7 = var6.replace(var3 + ",", "");
                } else if (var6.equalsIgnoreCase(var3)) {
                    var7 = "null";
                } else if (var6.contains("," + var3) && !var6.contains("," + var3 + ",")) {
                    var7 = var6.replace("," + var3, "");
                } else if (var6.contains("," + var3 + ",")) {
                    var7 = var6.replace("," + var3 + ",", "");
                }

                if (var7.equalsIgnoreCase("null")) {
                    var4.getPersistentDataContainer().remove(var5);
                } else {
                    var4.getPersistentDataContainer().set(var5, PersistentDataType.STRING, var7);
                }
            }
        }

        var1.setItemMeta(var4);
        return var1;
    }

    public ItemStack setNBT(ItemStack var1, String var2, String var3) {
        ItemMeta var4 = var1.getItemMeta();
        NamespacedKey var5 = new NamespacedKey(API.getPlugin(), var2);
        var4.getPersistentDataContainer().set(var5, PersistentDataType.STRING, var3);
        var1.setItemMeta(var4);
        return var1;
    }

    public ItemStack removeNBT(ItemStack var1, String var2) {
        ItemMeta var3 = var1.getItemMeta();
        NamespacedKey var4 = new NamespacedKey(API.getPlugin(), var2);
        var3.getPersistentDataContainer().remove(var4);
        var1.setItemMeta(var3);
        return var1;
    }

    public String getNBT(ItemStack var1, String var2) {
        if (var1 != null && var1.getType() != Material.AIR && var2 != null) {
            ItemMeta var3 = var1.getItemMeta();
            NamespacedKey var4 = new NamespacedKey(API.getPlugin(), var2);
            return (String)var3.getPersistentDataContainer().get(var4, PersistentDataType.STRING);
        } else {
            return null;
        }
    }

    public boolean hasNBT(ItemStack var1, String var2) {
        if (var1 != null && var1.getType() != Material.AIR) {
            ItemMeta var3 = var1.getItemMeta();
            NamespacedKey var4 = new NamespacedKey(API.getPlugin(), var2);
            return var3.getPersistentDataContainer().has(var4, PersistentDataType.STRING);
        } else {
            return false;
        }
    }

    public ItemStack weightGun(ItemStack var1, double var2) {
        ItemMeta var4 = var1.getItemMeta();
        Attribute var5 = Attribute.GENERIC_MOVEMENT_SPEED;
        AttributeModifier var6 = new AttributeModifier(UUID.randomUUID(), "CSP_Main", var2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        var4.removeAttributeModifier(var5);
        var4.addAttributeModifier(var5, var6);
        var1.setItemMeta(var4);
        return var1;
    }

    public void setItemInHand(org.bukkit.entity.Player var1, ItemStack var2) {
        var1.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        var1.getInventory().setItemInMainHand(var2);
    }

    public void entityWalk(Location var1, org.bukkit.entity.Entity var2) {
        net.minecraft.world.entity.Entity var3 = ((CraftEntity)var2).getHandle();
        PathEntity var4 = ((EntityInsentient)var3).J().a(var1.getX(), var1.getY(), var1.getZ(), 1);
        if (var4 != null) {
            ((EntityInsentient)var3).J().a(var4, 1.0);
            ((EntityInsentient)var3).J().a(1.0);
        }
    }

    public void entityStopWalk(Entity var1) {
        net.minecraft.world.entity.Entity var2 = ((CraftEntity)var1).getHandle();
        PathEntity var3 = ((EntityInsentient)var2).J().a(var1.getLocation().getX(), var1.getLocation().getY(), var1.getLocation().getZ(), 1);
        if (var3 != null) {
            ((EntityInsentient)var2).J().a(var3, 1.0);
            ((EntityInsentient)var2).J().a(1.0);
        }

    }

    public ItemStack setUnbreakable(ItemStack var1) {
        ItemMeta var2 = var1.getItemMeta();
        if (!var2.isUnbreakable()) {
            var2.setUnbreakable(true);
        }

        var1.setItemMeta(var2);
        return var1;
    }

    public void packetHelmet(org.bukkit.entity.Player var1, ItemStack var2) {
        ArrayList var3 = new ArrayList();
        var3.add(new Pair(EnumItemSlot.f, CraftItemStack.asNMSCopy(var2)));
        PacketPlayOutEntityEquipment var4 = new PacketPlayOutEntityEquipment(var1.getEntityId(), var3);
        ((CraftPlayer)var1).getHandle().c.a(var4);
    }

    public void packetOffHand(org.bukkit.entity.Player var1, ItemStack var2) {
        ArrayList var3 = new ArrayList();
        var3.add(new Pair(EnumItemSlot.b, CraftItemStack.asNMSCopy(var2)));
        Iterator var4 = var1.getWorld().getPlayers().iterator();

        while(var4.hasNext()) {
            Player var5 = (Player)var4.next();
            PacketPlayOutEntityEquipment var6 = new PacketPlayOutEntityEquipment(var1.getEntityId(), var3);
            ((CraftPlayer)var5).getHandle().c.a(var6);
        }

    }

    public void spawnParticle(String var1, Location var2, double var3, double var5, double var7, int var9, double var10, String var12) {
        try {
            if (var1.equals("REDSTONE")) {
                Color var13 = Color.fromRGB((int)var3, (int)var5, (int)var7);
                float var14 = (float)var10;
                Particle.DustOptions var15 = new Particle.DustOptions(var13, var14);
                var2.getWorld().spawnParticle(Particle.valueOf(var1), var2, var9, 0.0, 0.0, 0.0, var10, var15, true);
            } else {
                var2.getWorld().spawnParticle(Particle.valueOf(var1), var2, var9, var3, var5, var7, var10, (Object)null, true);
            }
        } catch (IllegalArgumentException var16) {
            Bukkit.getConsoleSender().sendMessage("§cInvalid particle found in " + var12 + " (" + var1 + ")");
        }

    }

    public void cameraRecoil(org.bukkit.entity.Player var1, float var2, float var3) {
        PacketPlayOutPosition var4 = new PacketPlayOutPosition(0.0, 0.0, 0.0, var2, var3, this.set, 0);
        ((CraftPlayer)var1).getHandle().c.a(var4);
    }

    public void changeExp(org.bukkit.entity.Player var1, float var2, int var3) {
        var1.sendExperienceChange(var2, var3);
    }

    public void setHandNBT(LivingEntity var1, boolean var2, String var3, String var4) {
        ItemStack var5 = var2 ? var1.getEquipment().getItemInMainHand() : var1.getEquipment().getItemInOffHand();
        ItemMeta var6 = var5.getItemMeta();
        NamespacedKey var7 = new NamespacedKey(API.getPlugin(), var3);
        var6.getPersistentDataContainer().set(var7, PersistentDataType.STRING, var4);
        var5.setItemMeta(var6);
    }

    public SimpleCommandMap getServerCommandMap() {
        return ((CraftServer)Bukkit.getServer()).getCommandMap();
    }
}
