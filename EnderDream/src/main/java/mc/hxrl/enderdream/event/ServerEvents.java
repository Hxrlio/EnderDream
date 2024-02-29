package mc.hxrl.enderdream.event;

import mc.hxrl.enderdream.EnderDream;
import mc.hxrl.enderdream.data.DragonKilledSavedData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class ServerEvents {
	
	@EventBusSubscriber(modid = EnderDream.MODID)
	public static class ServerForgeEvents {
		
		@SubscribeEvent
		public static void sleepingTime(SleepingTimeCheckEvent e) {
			
			DragonKilledSavedData dragonKilledSavedData = DragonKilledSavedData.get();
			
			if (dragonKilledSavedData.DRAGON_KILLED == 1) {
				return;
			}
			
			Player player = e.getPlayer();
			
			if (player.level.isDay()) {
				return;
			}
			
			int time = player.getSleepTimer();
			
			if (time == 20) {
				// tell client to make particles
				int dir = player.getBedOrientation().get2DDataValue();
				double x = player.getX();
				double y = player.getY();
				double z = player.getZ();
				switch (dir) {
					case 0:
						z -= 1;
						break;
					case 1:
						x += 1;
						break;
					case 2:
						z += 1;
						break;
					case 3:
						x -= 1;
						break;
				}
				ServerLevel sLevel = (ServerLevel)player.level;
				sLevel.sendParticles(ParticleTypes.CRIT, x, y, z, 10, 0, 0, 0, 0);
				//sLevel.sendParticles(ParticleTypes.DRAGON_BREATH, player.getX(), player.getY() - 0.5, player.getZ(), 100, 1, 0, 1, 0);
			}
			if (time == 60) {
				// dragon roar
			}
			if (time == 80) {
				// 1 second of blindness
			}
			if (time == 99) {
				player.die(DamageSource.GENERIC);
				player.stopSleeping();
			}
		}
	}
}
