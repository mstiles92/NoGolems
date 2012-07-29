package com.mstiles92.nogolems;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class NoGolemsEventListener implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onGolemBuild(BlockPlaceEvent e) {
		if (e.isCancelled()) return;
		if ((e.getBlock() == null) || (e.getPlayer() == null)) return;
		if ((e.getBlock().getType() != Material.PUMPKIN) && (e.getBlock().getType() != Material.JACK_O_LANTERN)) return;
		
		Location location = e.getBlock().getLocation();
		
		// Check for Snow Golem
		if ((location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ()).getType() == Material.SNOW_BLOCK)
				&& (location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 2, location.getBlockZ()).getType() == Material.SNOW_BLOCK)) {
			if (!e.getPlayer().hasPermission("nogolems.allow.snow")) {
				e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to create Snow Golems.");
				e.setCancelled(true);
				return;
			}
			else return;
		}
		
		// Check for Iron Golem
		if ((location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ()).getType() == Material.IRON_BLOCK)
				&& (location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 2, location.getBlockZ()).getType() == Material.IRON_BLOCK)
				&& ( (location.getWorld().getBlockAt(location.getBlockX() + 1, location.getBlockY() - 1, location.getBlockZ()).getType() == Material.IRON_BLOCK) &&
				(location.getWorld().getBlockAt(location.getBlockX() - 1, location.getBlockY() - 1, location.getBlockZ()).getType() == Material.IRON_BLOCK)) 
				|| ((location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ() - 1).getType() == Material.IRON_BLOCK)
				&& (location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ() + 1).getType() == Material.IRON_BLOCK))) {
			if (!e.getPlayer().hasPermission("nogolems.allow.iron")) {
				e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to create Iron Golems.");
				e.setCancelled(true);
				return;
			}
			else return;
		}
	}
}
