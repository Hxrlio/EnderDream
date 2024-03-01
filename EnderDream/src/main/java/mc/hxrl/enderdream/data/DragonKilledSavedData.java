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
		//get the overworld data storage and have a look for our custom data, if it's not there then put it in, otherwise just get what's in there.
		DimensionDataStorage storage = ServerLifecycleHooks.getCurrentServer().getLevel(Level.OVERWORLD).getDataStorage();
		return storage.computeIfAbsent(DragonKilledSavedData::new, DragonKilledSavedData::new, "dragonkilled");
	}
	
	public void dragonKilled() {
		//when the dragon is killed, mark it as such.
		DRAGON_KILLED = 1;
		//setDirty tells the game to save our data when it next does that.
		setDirty();
	}
	
	public DragonKilledSavedData() {
		//if our SavedData doesn't exist, add it.
		DRAGON_KILLED = 0;
		setDirty();
	}
	
	public DragonKilledSavedData(CompoundTag tag) {
		DRAGON_KILLED = tag.getInt("dragonkilled");
	}
	
	@Override
	public CompoundTag save(CompoundTag tag) {
		//we use an int value to store whether the dragon has been killed as there is no boolean. There might be a better option, but the amount it matters is 0.
		IntTag i = IntTag.valueOf(DRAGON_KILLED);
		tag.put("dragonkilled", i);
		return tag;
	}
}
