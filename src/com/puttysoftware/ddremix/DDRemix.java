/*  DDRemix: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.ddremix;

import org.retropipes.diane.Diane;
import org.retropipes.diane.gui.dialog.CommonDialogs;
import org.retropipes.diane.integration.Integration;

import com.puttysoftware.ddremix.creatures.AbstractCreature;
import com.puttysoftware.ddremix.prefs.PreferencesLauncher;

public class DDRemix {
    // Constants
    private static Application application;
    private static final String PROGRAM_NAME = "DDRemix";
    private static final String ERROR_MESSAGE = "Perhaps a bug is to blame for this error message.\n"
            + "Include the error log with your bug report.\n"
            + "Email bug reports to: products@puttysoftware.com\n"
            + "Subject: DDRemix Bug Report";
    private static final String ERROR_TITLE = "DDRemix Error";
    private static final int BATTLE_MAZE_SIZE = 16;

    // Methods
    public static Application getApplication() {
        return DDRemix.application;
    }
    
    public static void logError(final Throwable t) {
        // Display error message
        CommonDialogs.showErrorDialog(DDRemix.ERROR_MESSAGE,
                DDRemix.ERROR_TITLE);
        Diane.handleError(t);
    }

    public static void preInit() {
        // Compute action cap
        AbstractCreature.computeActionCap(DDRemix.BATTLE_MAZE_SIZE,
                DDRemix.BATTLE_MAZE_SIZE);
    }

    public static void main(final String[] args) {
        try {
            // Pre-Init
            DDRemix.preInit();
            // Integrate with host platform
            Integration i = Integration.integrate();
            DDRemix.application = new Application();
            DDRemix.application.postConstruct();
            Application.playLogoSound();
            DDRemix.application.getGUIManager().showGUI();
            // Register platform hooks
            i.setAboutHandler(DDRemix.application.getAboutDialog());
            i.setOpenFileHandler(DDRemix.application.getMazeManager());
            i.setPreferencesHandler(new PreferencesLauncher());
            i.setQuitHandler(DDRemix.application.getGUIManager());
            // Set up Common Dialogs
            CommonDialogs.setDefaultTitle(DDRemix.PROGRAM_NAME);
            CommonDialogs.setIcon(Application.getMicroLogo());
        } catch (final Throwable t) {
            DDRemix.logError(t);
        }
    }
}
