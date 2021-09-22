package dev.hely.scoreboard;

import dev.hely.scoreboard.provider.impl.ExampleProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class ScoreboardAPI extends JavaPlugin {


	private static Optional<ScoreboardAPI> instance;

	@Override
	public void onEnable() {
		instance = Optional.<ScoreboardAPI>of(this);

		new ScoreboardManager(this, new ExampleProvider());

	}

	public static ScoreboardAPI getInstance() {
		return instance.orElseThrow(() -> new IllegalStateException("ScoreboardAPI instance is null"));
	}
}