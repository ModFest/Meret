package symbolics.division.meret;

import dev.doublekekse.area_lib.component.AreaDataComponentType;
import dev.doublekekse.area_lib.registry.AreaDataComponentTypeRegistry;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Meret implements ModInitializer {
    public static final String MOD_ID = "meret";
    public static final String MF_ID = "mf121_music";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final AreaDataComponentType<AreaMusicDelay> AREA_MUSIC_DELAY_DATA_COMPONENT = AreaDataComponentTypeRegistry.register(id("area_delay"), AreaMusicDelay::new);

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public static final class OVERRIDES {
//		public static final ResourceLocation STONE_ID = ResourceLocation.fromNamespaceAndPath(MF_ID, "override.industrial");
//		public static final SoundEvent STONE = SoundEvent.createVariableRangeEvent(STONE_ID);
//		public static final ResourceLocation IRON_ID = ResourceLocation.fromNamespaceAndPath(MF_ID, "override.industrial");
//		public static final SoundEvent IRON = SoundEvent.createVariableRangeEvent(IRON_ID);
//		public static final ResourceLocation INDUSTRIAL_ID = ResourceLocation.fromNamespaceAndPath(MF_ID, "override.industrial");
//		public static final SoundEvent INDUSTRIAL = SoundEvent.createVariableRangeEvent(INDUSTRIAL_ID);
//		public static final ResourceLocation INFO_ID = ResourceLocation.fromNamespaceAndPath(MF_ID, "override.industrial");
//		public static final SoundEvent INFO = SoundEvent.createVariableRangeEvent(INFO_ID);

    }

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) -> {
                MeretCommand.register(dispatcher);
            }
        );

        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

//		Registry.register(BuiltInRegistries.SOUND_EVENT, OVERRIDES.INDUSTRIAL_ID, OVERRIDES.INDUSTRIAL);
//		Registry.register(Registries.SOUND_EVENT, OVERRIDES.INDUSTRIAL_ID, OVERRIDES.INDUSTRIAL);

    }
}