package dev.hely.scoreboard.provider.impl;

import dev.hely.scoreboard.provider.ScoreboardProvider;
import dev.hely.scoreboard.text.ScoreboardText;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class ExampleProvider extends ScoreboardProvider {
	
	@Override
	public String getTitle(Player p) {
		return p.getName() + "'s Provider";
	}
	
	/**
	 * You can use up to 15 Scoreboard lines.
	 * Max-length of your text is 32.
	 */
	@Override
	public List<ScoreboardText> getLines(Player p) {
		List<ScoreboardText> lines = new ArrayList<>();
		
		lines.add(new ScoreboardText("�6Name: �f" + p.getName()));
		lines.add(new ScoreboardText("�6Health: �f" + Math.round(p.getHealth()) + "/" + p.getMaxHealth()));
		lines.add(new ScoreboardText("�6Gamemode: �f" + p.getGameMode().name()));
		lines.add(new ScoreboardText("�6EXP-Level: �f" + p.getTotalExperience()));
		
		int potions = this.countPotions(p.getInventory());
		String output = (potions == 0 ? "None" : String.valueOf(potions));
		
		lines.add(new ScoreboardText("�6Potions: �f" + output));
		
		if (lines.size() >= 4 && lines.size() < 14) {
			lines.add(new ScoreboardText(" "));
			lines.add(new ScoreboardText("SB-API created by BeProud."));
		}
		
		return lines;
	}
	
	int countPotions(PlayerInventory inv) {
		if (inv == null) {
			return 0;
		}
		
		int potions = 0;
		
		for (ItemStack contents : inv.getContents()) {
			if (contents != null && contents.getType() == Material.POTION
					&& contents.getDurability() == (short) 16421) {
				potions++;
			}
		}
		
		return potions;
	}
	
}