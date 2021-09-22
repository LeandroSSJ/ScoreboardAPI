package dev.hely.scoreboard;

import dev.hely.scoreboard.provider.ScoreboardProvider;
import dev.hely.scoreboard.thread.ScoreboardThread;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ScoreboardManager implements Listener {

	private final JavaPlugin plugin;
	private final ConcurrentHashMap<Player, PlayerScoreboard> playerScoreboards;
	private ScoreboardThread scoreboardThread;
	private ScoreboardProvider provider;

	public ScoreboardManager(JavaPlugin plugin, ScoreboardProvider provider) {
		this.plugin = plugin;
		this.provider = provider;

		this.playerScoreboards = new ConcurrentHashMap<>();
		
		Bukkit.getOnlinePlayers().forEach(this::addPlayer);

		setup();
	}

	public void setup(){
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		if (this.scoreboardThread != null) {
			this.scoreboardThread.stop();
			this.scoreboardThread = null;
		}

		//Start Thread
		this.scoreboardThread = new ScoreboardThread(this);

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		addPlayer(p);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		removePlayer(p);
	}

	public void addPlayer(Player player) {
		if (!this.playerScoreboards.containsKey(player)) return;

		this.playerScoreboards.put(player, new PlayerScoreboard(provider, player));
	}

	public void removePlayer(Player player) {
		if (!this.playerScoreboards.containsKey(player)) return;

		this.playerScoreboards.remove(player).disappear();

	}
	

}