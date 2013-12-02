package com.mstiles92.plugins.nogolems.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock() == null || event.getPlayer() == null) {
            return;
        }

        Block block = event.getBlock();

        if (block.getType() != Material.PUMPKIN && block.getType() != Material.JACK_O_LANTERN) {
            return;
        }

        // Check for snow golem
        if (block.getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK
                && block.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK) {
            if (!event.getPlayer().hasPermission("plugins.allow.snow")) {
                event.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to build a Snow Golem.");
                event.setCancelled(true);
            }
            return;
        }

        // Check for Iron Golem
        if (block.getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK
                && block.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK
                && ((block.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getType() == Material.IRON_BLOCK
                && block.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getType() == Material.IRON_BLOCK)
                || (block.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getType() == Material.IRON_BLOCK
                && block.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getType() == Material.IRON_BLOCK))) {
            if (!event.getPlayer().hasPermission("plugins.allow.iron")) {
                event.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to build an Iron Golem.");
                event.setCancelled(true);
            }
            return;
        }
    }
}
