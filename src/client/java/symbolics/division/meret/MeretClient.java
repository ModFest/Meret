package symbolics.division.meret;

import com.mojang.datafixers.util.Pair;
import dev.doublekekse.area_lib.Area;
import dev.doublekekse.area_lib.data.AreaClientData;
import dev.doublekekse.area_lib.data.AreaSavedData;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.Music;
import net.minecraft.world.item.JukeboxSong;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class MeretClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}

	@Nullable
	public static Music getOverride(LocalPlayer player) {
		if (player == null) return null;

		Registry<JukeboxSong> songRegistry = player.level().registryAccess().registry(Registries.JUKEBOX_SONG).orElse(null);

		if (songRegistry == null) {
			Meret.LOGGER.info("Could not get a handle on songRegistry, you will not hear any music.");
			return null;
		}

		AreaSavedData areaSavedData = AreaClientData.getClientLevelData();
		Area silentArea = areaSavedData.get(Meret.id("silent"));

		if(silentArea != null && silentArea.contains(player)) {
            return null;
		}

		// sorry jasmine  (´・ω・`)
		return songRegistry.getTagNames()
                .map(tag -> new Pair<>(tag, areaSavedData.get(tag.location())))
                .filter(p -> p.getSecond() != null && p.getSecond().contains(player))
				.max(Comparator.comparingInt(p -> p.getSecond().getPriority()))
				.flatMap(p -> songRegistry.getOrCreateTag(p.getFirst()).getRandomElement(player.getRandom()))
				.map(song -> new Music(song.value().soundEvent(), 20 * 3 * 60, 20 * 5 * 60, false))
				.orElse(null);
	}
}