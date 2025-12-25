/*  DDRemix: An RPG
Copyright (C) 2011-2012 Eric Ahnell


Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.ddremix.descriptionmanagers;

import java.io.IOException;

import org.retropipes.diane.fileio.utility.ResourceStreamReader;

import com.puttysoftware.ddremix.DDRemix;
import com.puttysoftware.ddremix.creatures.races.RaceConstants;
import com.puttysoftware.ddremix.maze.Extension;

public class RaceDescriptionManager {
    public static String getRaceDescription(final int r) {
	final String name = RaceConstants.getRaceName(r).toLowerCase();
	try (final ResourceStreamReader rsr = new ResourceStreamReader(RaceDescriptionManager.class
		.getResourceAsStream("/com/puttysoftware/ddremix/resources/descriptions/race/" + name
			+ Extension.getInternalDataExtensionWithPeriod()))) {
	    // Fetch description
	    final String desc = rsr.readString();
	    return desc;
	} catch (final IOException e) {
	    DDRemix.logError(e);
	    return null;
	}
    }
}
