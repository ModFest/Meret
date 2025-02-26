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
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final AreaDataComponentType<AreaMusicComponent> AREA_MUSIC_DATA_COMPONENT = AreaDataComponentTypeRegistry.registerTracking(id("area_music"), AreaMusicComponent::new);

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) -> {
                MeretCommand.register(dispatcher);
            }
        );
    }
}