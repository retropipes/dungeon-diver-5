package com.puttysoftware.ddremix.maze;

import java.io.IOException;

import org.retropipes.diane.fileio.XDataReader;
import org.retropipes.diane.fileio.XDataWriter;

import com.puttysoftware.ddremix.DDRemix;
import com.puttysoftware.ddremix.game.FileHooks;

public class SuffixHandler implements SuffixIO {
    @Override
    public void readSuffix(final XDataReader reader, final int formatVersion)
            throws IOException {
        DDRemix.getApplication().getGameManager();
        FileHooks.loadGameHook(reader);
    }

    @Override
    public void writeSuffix(final XDataWriter writer) throws IOException {
        DDRemix.getApplication().getGameManager();
        FileHooks.saveGameHook(writer);
    }
}
