package symbolics.division.meret;

import dev.doublekekse.area_lib.data.AreaClientData;
import dev.doublekekse.area_lib.data.AreaSavedData;
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

public class MeretClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}

	@Nullable
	public static Music getOverride(LocalPlayer player) {
		if (player == null) return null;

		AreaSavedData.IdentifiableArea potentialArea = AreaClientData.getClientLevelData()
				.find(player.level(), player.position());

		if (potentialArea == null) return null;

		System.out.println("found area " + potentialArea.id().toString());

		Registry<JukeboxSong> songRegistry =  player.level().registryAccess().registry(Registries.JUKEBOX_SONG).orElse(null);

		if (songRegistry == null) return null;

		TagKey<JukeboxSong> musicTag = TagKey.create(
				Registries.JUKEBOX_SONG,
				potentialArea.id()
		);

		HolderSet.Named<JukeboxSong> taggedHolders = songRegistry.getOrCreateTag(musicTag);
		Holder<JukeboxSong> song = taggedHolders.getRandomElement(player.getRandom()).orElse(null);
		if (song == null) return null;

		// vanilla game music values
		return new Music(song.value().soundEvent(), 300, 300, false);
	}
}