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

import com.mstiles92.plugins.nogolems.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class NoGolemsPlugin extends JavaPlugin {
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			this.getLogger().warning("Metrics failed to start!");
		}
	}
}
