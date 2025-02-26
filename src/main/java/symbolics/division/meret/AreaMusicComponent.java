package symbolics.division.meret;

import dev.doublekekse.area_lib.component.AreaDataComponent;
import dev.doublekekse.area_lib.data.AreaSavedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.sounds.Music;

public class AreaMusicComponent implements AreaDataComponent {
    public Music music;

    AreaMusicComponent() {

    }

    public AreaMusicComponent(Music music) {
        this.music = music;
    }

    @Override
    public void load(AreaSavedData areaSavedData, CompoundTag compoundTag) {
        music = Music.CODEC.decode(NbtOps.INSTANCE, compoundTag.get("music")).getOrThrow().getFirst();
    }

    @Override
    public CompoundTag save() {
        var tag = new CompoundTag();
        tag.put("music", Music.CODEC.encode(music, NbtOps.INSTANCE, null).getOrThrow());

        return tag;
    }
}
