package dev.hely.scoreboard.text;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ScoreboardText {
	
	private final String text;
	private final String extendedText;
	
	private boolean extended = false;
	
	public ScoreboardText(String text) {
		Preconditions.checkState(text.length() <= 32, "Text can't be longer than 32 chars.");
		
		if (text.length() > 16) {
			this.extended = true;
			
			this.extendedText = text.substring(16);
			this.text = text.substring(0, 16);
		} else { 
			this.text = text;
			this.extendedText = StringUtils.EMPTY;
		}
	}


}