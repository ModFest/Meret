package symbolics.division.meret.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicManager.class)
public class MusicManagerMixin {
    @Shadow
    @Final
    private RandomSource random;
    @Unique
    private int actualNextSongDelay;

    @Inject(method = "tick", at = @At("HEAD"))
    void tick(CallbackInfo ci) {
        actualNextSongDelay--;
    }

    @WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sounds/MusicManager;startPlaying(Lnet/minecraft/sounds/Music;)V"))
    boolean startPlayingCondition(MusicManager instance, Music music) {
        return actualNextSongDelay < 0;
    }

    @Inject(method = "startPlaying", at = @At("HEAD"))
    void startPlaying(Music music, CallbackInfo ci) {
        actualNextSongDelay = Mth.nextInt(random, music.getMinDelay(), music.getMaxDelay());
    }
}
