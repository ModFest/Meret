package symbolics.division.meret.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import dev.doublekekse.area_lib.data.AreaClientData;
import dev.doublekekse.area_lib.data.AreaSavedData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.JukeboxSong;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow public LocalPlayer player;

    @Inject(
            method = "getSituationalMusic",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/Optionull;map(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;")
    )
    public void modifyGetSituationalMusic(CallbackInfoReturnable<Music> ci) {
        if (this.player != null) {
            AreaSavedData.IdentifiableArea potentialArea = AreaClientData.getClientLevelData().find(this.player.level(), this.player.position());
            Registry<JukeboxSong> songRegistry = this.player.level().registryAccess().registry(Registries.JUKEBOX_SONG).get();

            // I have this
            ResourceLocation musicTypeTag = potentialArea.id();
            // I need to get a random jukebox sound tagged with this


            
        }
    }
}
