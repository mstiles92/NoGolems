/**
 * This document is a part of the source code and related artifacts for
 * NoGolems, an open source Bukkit plugin for blocking players from building
 * snow/iron golems via permission settings.
 *
 * http://dev.bukkit.org/server-mods/plugins/
 * http://github.com/mstiles92/NoGolems
 *
 * Copyright � 2013 Matthew Stiles (mstiles92)
 *
 * Licensed under the Common Development and Distribution License Version 1.0
 * You may not use this file except in compliance with this License.
 *
 * You may obtain a copy of the CDDL-1.0 License at
 * http://opensource.org/licenses/CDDL-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the license.
 */

package com.mstiles92.plugins.nogolems;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class NoGolemsPlugin extends JavaPlugin implements Listener {
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			this.getLogger().warning("Metrics failed to start!");
		}
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
			if (!e.getPlayer().hasPermission("plugins.allow.snow")) {
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
			if (!e.getPlayer().hasPermission("plugins.allow.iron")) {
				e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to build an Iron Golem.");
				e.setCancelled(true);
			}
			return;
		}
	}
}
