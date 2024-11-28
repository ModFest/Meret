package symbolics.division.meret;

import com.mojang.datafixers.util.Pair;
import dev.doublekekse.area_lib.Area;
import dev.doublekekse.area_lib.data.AreaClientData;
import dev.doublekekse.area_lib.data.AreaSavedData;
import it.unimi.dsi.fastutil.PriorityQueue;
import it.unimi.dsi.fastutil.objects.ObjectArrayPriorityQueue;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.Music;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.JukeboxSong;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Objects;

public class MeretClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}

	@Nullable
	public static Music getOverride(LocalPlayer player) {
		if (player == null) return null;

		Area[] areas = AreaClientData.getClientLevelData().getAreas().toArray(Area[]::new);

		PriorityQueue<Area> queue = new ObjectArrayPriorityQueue<>(areas.length, Comparator.comparingInt(Area::getPriority));
//		for (Area potentialArea : areas) {
//			queue.enqueue(potentialArea);
//		}

		Registry<JukeboxSong> songRegistry =  player.level().registryAccess().registry(Registries.JUKEBOX_SONG).orElse(null);

		if (songRegistry == null) {
			Meret.LOGGER.info("Could not get a handle on songRegistry, you will not hear any music.");
			return null;
		}

		var songTags = songRegistry.getTagNames().toList();


//		for (var tag : songTags)  {
//			Area area = AreaClientData.getClientLevelData().get(tag.location());
//			if (area != null && area.contains(player))
//				queue.enqueue(area);
//		}

//		return null;
//		if (queue.isEmpty()) return null;

//		songRegistry.getOrCreateTag()



		// sorry jasmine  (´・ω・`)
		return songRegistry.getTagNames()
                .map(tag -> new Pair<>(tag, AreaClientData.getClientLevelData().get(tag.location())))
                .filter(p -> p.getSecond() != null && p.getSecond().contains(player))
				.max(Comparator.comparingInt(p -> p.getSecond().getPriority()))
				.flatMap(p -> songRegistry.getOrCreateTag(p.getFirst()).getRandomElement(player.getRandom()))
				.map(song -> new Music(song.value().soundEvent(), 10, 10, false))
				.orElse(null);

//		return new Music(song.value().soundEvent(), 10, 10, false);
////		return new Music(song.value().soundEvent(), 20 * 3 * 60, 20 * 5  * 60, false);
	}
}