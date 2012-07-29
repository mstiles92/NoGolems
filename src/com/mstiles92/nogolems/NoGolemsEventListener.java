package com.mstiles92.nogolems;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class NoGolemsEventListener implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onGolemBuild(CreatureSpawnEvent e) {
		
	}
}
