package info.u_team.music_player.gui;

import java.util.Collection;
import java.util.stream.Collectors;

import net.minecraft.client.gui.*;

public interface BetterNestedGui extends INestedGuiEventHandler {
	
	default Collection<IGuiEventListener> getEventListenersForPos(double mouseX, double mouseY) {
		return getEventListeners().stream().filter(listener -> listener.isMouseOver(mouseX, mouseY)).collect(Collectors.toList());
	}
	
	@Override
	public default boolean mouseReleased(double mouseX, double mouseY, int button) {
		setDragging(false);
		final Collection<IGuiEventListener> list = getEventListenersForPos(mouseX, mouseY);
		getEventListenersForPos(mouseX, mouseY).forEach(listener -> listener.mouseReleased(mouseX, mouseY, button));
		return !list.isEmpty();
	}
	
	@Override
	public default boolean mouseScrolled(double mouseX, double mouseY, double button) {
		final Collection<IGuiEventListener> list = getEventListenersForPos(mouseX, mouseY);
		getEventListenersForPos(mouseX, mouseY).forEach(listener -> listener.mouseScrolled(mouseX, mouseY, button));
		return !list.isEmpty();
	}
	
}
