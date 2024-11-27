package symbolics.division.meret.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import symbolics.division.meret.MeretClient;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow public LocalPlayer player;

    @Inject(
            method = "getSituationalMusic",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/Optionull;map(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;"),
            cancellable = true
    )
    public void modifyGetSituationalMusic(CallbackInfoReturnable<Music> ci) {
        Music override = MeretClient.getOverride(this.player);
        if (override == null) {
            // override vanilla music always
            ci.setReturnValue(new Music(Holder.direct(SoundEvents.EMPTY), 10, 10, false));
        } else {
            ci.setReturnValue(override);
        }
        ci.cancel();
    }
}
