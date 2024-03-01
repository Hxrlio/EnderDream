package mc.hxrl.enderdream.event;

import mc.hxrl.enderdream.EnderDream;
import mc.hxrl.enderdream.data.DragonKilledSavedData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
				sLevel.sendParticles(ParticleTypes.DRAGON_BREATH, x, y - 0.5, z, 100, 2, 0, 2, 0);
				
			}
			
			if (time == 35) {
				
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
				sLevel.sendParticles(ParticleTypes.DRAGON_BREATH, x, y - 0.5, z, 800, 1.5, 0, 1.5, 0);
				
			}
			
			if (time == 50) {
				
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
				sLevel.sendParticles(ParticleTypes.DRAGON_BREATH, x, y, z, 200, 0.5, 0, 0.5, 0);
			}
			
			if (time == 60) {
				//blindness (5s) night vision (4.5s)
				player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0, false, false));
				player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 90, 0, false, false));
				
			}
			
			if (time == 80) {
				//roar
			}
			
			if (time == 99) {
				
				player.die(DamageSource.GENERIC);
				player.stopSleeping();
				
			}
		}
	}
}
