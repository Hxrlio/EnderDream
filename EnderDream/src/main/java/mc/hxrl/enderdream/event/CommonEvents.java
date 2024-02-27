package mc.hxrl.enderdream.event;

import mc.hxrl.enderdream.EnderDream;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class CommonEvents {
	
	@EventBusSubscriber(modid = EnderDream.MODID)
	public static class CommonForgeEvents {
		
		@SubscribeEvent()
		public static void sleepingTime(SleepingTimeCheckEvent e) {
			Player player = e.getPlayer();
			if (player.level.isDay()) {
				return;
			}
			int time = player.getSleepTimer();
			if (time == 99) {
				player.die(DamageSource.GENERIC);
				player.stopSleeping();
			}
		}
	}
}
