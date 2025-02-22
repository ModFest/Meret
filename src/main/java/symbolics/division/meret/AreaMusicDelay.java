package symbolics.division.meret;

import dev.doublekekse.area_lib.component.AreaDataComponent;
import dev.doublekekse.area_lib.data.AreaSavedData;
import net.minecraft.nbt.CompoundTag;

public class AreaMusicDelay implements AreaDataComponent {
    public int minDelay;
    public int maxDelay;

    AreaMusicDelay() {

    }

    public AreaMusicDelay(int minDelay, int maxDelay) {
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
    }

    @Override
    public void load(AreaSavedData areaSavedData, CompoundTag compoundTag) {
        minDelay = compoundTag.getInt("minDelay");
        maxDelay = compoundTag.getInt("maxDelay");
    }

    @Override
    public CompoundTag save() {
        var tag = new CompoundTag();

        tag.putInt("minDelay", minDelay);
        tag.putInt("maxDelay", maxDelay);

        return tag;
    }
}
