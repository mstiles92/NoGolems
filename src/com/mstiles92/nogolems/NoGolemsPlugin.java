package com.mstiles92.nogolems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoGolemsPlugin extends JavaPlugin implements Listener {
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.getBlock() == null || e.getPlayer() == null) {
			return;
		}
		
		final Block b = e.getBlock();
		
		if (b.getType() != Material.PUMPKIN && b.getType() != Material.JACK_O_LANTERN) {
			return;
		}
		
		// Check for snow golem
		if (b.getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK
				&& b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK) {
			if (!e.getPlayer().hasPermission("nogolems.allow.snow")) {
				e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to build a Snow Golem.");
				e.setCancelled(true);
			}
			return;
		}
		
		// Check for Iron Golem
		if (b.getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK
				&& b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK
				&& ((b.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getType() == Material.IRON_BLOCK
					&& b.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getType() == Material.IRON_BLOCK)
				|| (b.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getType() == Material.IRON_BLOCK
					&& b.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getType() == Material.IRON_BLOCK))) {
			if (!e.getPlayer().hasPermission("nogolems.allow.iron")) {
				e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to build an Iron Golem.");
				e.setCancelled(true);
			}
			return;
		}
	}
}
