package mc.hxrl.enderdream.event;

import mc.hxrl.enderdream.EnderDream;
import mc.hxrl.enderdream.data.DragonKilledSavedData;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class CommonEvents {
	
	@EventBusSubscriber(modid = EnderDream.MODID)
	public static class CommonForgeEvents {
		
		@SubscribeEvent
		public static void entityDied(LivingDeathEvent e) {
			
			Entity entity = e.getEntity();
			//only the server cares about if the dragon dies
			if (entity.level.isClientSide) {
				return;
			}
			DragonKilledSavedData dragonKilledSavedData = DragonKilledSavedData.get();
			//we don't care about the death of entities if the dragon has already died.
			if (dragonKilledSavedData.DRAGON_KILLED == 1) {
				return;
			}
			
			DragonKilledSavedData.get();
			//we only care if the thing that died is the dragon
			if (!entity.getType().toShortString().equals("ender_dragon")) {
				return;
			}
			//if it is then lets mark it.
			dragonKilledSavedData.dragonKilled();
			
		}
	}
}
