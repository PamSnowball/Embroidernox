package snowball.embroidernox.component.death;

import java.util.List;

import snowball.embroidernox.util.Utils;

public class FadeDeath implements IDeath {
	float fadeTime;
		
	public FadeDeath(float fadeTime) {
		this.fadeTime = fadeTime;
	}

	public void death(List<String> death) {
		death.add(Utils.value("FADE_DEATH", fadeTime));
	}
}
