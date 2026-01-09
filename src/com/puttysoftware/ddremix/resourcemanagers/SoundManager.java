/*  DDRemix: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.ddremix.resourcemanagers;

import org.retropipes.diane.asset.sound.DianeSoundPlayer;
import org.retropipes.diane.random.RandomRange;

import com.puttysoftware.ddremix.prefs.PreferencesManager;

public class SoundManager {
    private static final String DEFAULT_LOAD_PATH = "/com/puttysoftware/ddremix/resources/sounds/";
    private static String LOAD_PATH = SoundManager.DEFAULT_LOAD_PATH;
    private static Class<?> LOAD_CLASS = SoundManager.class;

    public static void playSound(final int soundID) {
	try {
	    if (PreferencesManager.getSoundsEnabled()) {
		int offset = 0;
		if (soundID == SoundConstants.SOUND_WALK) {
		    final RandomRange rSound = new RandomRange(0, 2);
		    offset = rSound.generate();
		}
		final String soundName = SoundConstants.getSoundName(soundID + offset);
		DianeSoundPlayer.playSource(
			SoundManager.LOAD_CLASS.getResource(SoundManager.LOAD_PATH + soundName.toLowerCase() + ".wav"));
	    }
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    // Do nothing
	}
    }
}