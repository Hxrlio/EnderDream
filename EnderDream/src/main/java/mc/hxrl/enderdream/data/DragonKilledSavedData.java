package mc.hxrl.enderdream.data;

import javax.annotation.Nonnull;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraftforge.server.ServerLifecycleHooks;

public class DragonKilledSavedData extends SavedData {
	
	public int DRAGON_KILLED;
	
	@Nonnull
	public static DragonKilledSavedData get() {
		DimensionDataStorage storage = ServerLifecycleHooks.getCurrentServer().getLevel(Level.OVERWORLD).getDataStorage();
		return storage.computeIfAbsent(DragonKilledSavedData::new, DragonKilledSavedData::new, "dragonkilled");
	}
	
	public void dragonKilled() {
		DRAGON_KILLED = 1;
		setDirty();
	}
	
	public DragonKilledSavedData() {
		DRAGON_KILLED = 0;
		setDirty();
	}
	
	public DragonKilledSavedData(CompoundTag tag) {
		DRAGON_KILLED = tag.getInt("dragonkilled");
	}
	
	@Override
	public CompoundTag save(CompoundTag tag) {
		IntTag i = IntTag.valueOf(DRAGON_KILLED);
		tag.put("dragonkilled", i);
		return tag;
	}
}
