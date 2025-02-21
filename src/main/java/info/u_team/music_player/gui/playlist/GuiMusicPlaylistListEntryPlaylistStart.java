package info.u_team.music_player.gui.playlist;

import static info.u_team.music_player.init.MusicPlayerLocalization.*;

import java.util.*;

import com.mojang.blaze3d.matrix.MatrixStack;

import info.u_team.music_player.lavaplayer.api.audio.IAudioTrack;
import info.u_team.music_player.musicplayer.playlist.*;
import info.u_team.music_player.util.TimeUtil;

public class GuiMusicPlaylistListEntryPlaylistStart extends GuiMusicPlaylistListEntryFunctions {
	
	private final String name;
	private final String duration;
	
	private final List<GuiMusicPlaylistListEntryPlaylistTrack> entries;
	
	public GuiMusicPlaylistListEntryPlaylistStart(GuiMusicPlaylistList guilist, Playlists playlists, Playlist playlist, LoadedTracks loadedTracks) {
		super(guilist, playlists, playlist, loadedTracks, loadedTracks.getFirstTrack());
		name = loadedTracks.getTitle();
		
		final List<IAudioTrack> tracks = loadedTracks.getTrackList().getTracks();
		
		if (!tracks.parallelStream().anyMatch(track -> track.getInfo().isStream())) {
			duration = TimeUtil.timeConversion(tracks.parallelStream().mapToLong(track -> track.getDuration()).sum() / 1000);
		} else {
			duration = getTranslation(GUI_TRACK_DURATION_UNDEFINED);
		}
		
		entries = new ArrayList<>();
	}
	
	@Override
	public void drawEntryExtended(MatrixStack matrixStack, int entryX, int entryY, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean mouseInList, float partialTicks) {
		minecraft.fontRenderer.drawString(matrixStack, name, entryX + 5, entryY + 15, 0xF4E242);
		minecraft.fontRenderer.drawString(matrixStack, duration, entryX + entryWidth - 140, entryY + 15, 0xFFFF00);
	}
	
	public void addEntry(GuiMusicPlaylistListEntryPlaylistTrack entry) {
		entries.add(entry);
	}
	
	@Override
	protected boolean isPlaying() {
		return entries.stream().anyMatch(entry -> entry.getStart() == this && entry.getTrack() == getCurrentlyPlaying());
	}
}
