package info.u_team.music_player.init;

import org.apache.commons.lang3.tuple.Pair;

import info.u_team.music_player.MusicPlayerMod;
import info.u_team.u_team_core.api.construct.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Construct(modid = MusicPlayerMod.MODID)
public class MusicPlayerCommonConstruct implements IModConstruct {
	
	@Override
	public void construct() {
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
	}
	
}
