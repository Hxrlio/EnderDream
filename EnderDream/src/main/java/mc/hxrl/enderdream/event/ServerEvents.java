package mc.hxrl.enderdream.event;

import mc.hxrl.enderdream.EnderDream;
import mc.hxrl.enderdream.data.DragonKilledSavedData;
import mc.hxrl.enderdream.network.EnderDreamPacketHandler;
import mc.hxrl.enderdream.network.PacketDragonRoar;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.NetworkDirection;

public class ServerEvents {
	
	@EventBusSubscriber(modid = EnderDream.MODID)
	public static class ServerForgeEvents {
		
		@SubscribeEvent
		public static void sleepingTime(SleepingTimeCheckEvent e) {
			//this is called whenever the player tries to or is sleeping.
			DragonKilledSavedData dragonKilledSavedData = DragonKilledSavedData.get();
			//if the dragon is dead, we don't need to haunt the player's sleep any longer.
			if (dragonKilledSavedData.DRAGON_KILLED == 1) {
				return;
			}
			
			Player player = e.getPlayer();
			//this event can be called when the player fails to sleep due to it being daytime. We don't care about that scenario.
			if (player.level.isDay()) {
				return;
			}
			//now that only successful sleeping ticks are involved we can get how long the player has been asleep
			int time = player.getSleepTimer();
			//sleep takes 100 ticks, so we can do whatever we want right up to the end.
			if (time == 15) {
				//we get the player's coordinates (directly on their head) then shift them slightly depending on which way the bed is facing.
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
				//tell the client to spawn 100 DRAGON_BREATH particles in a horizontal circle of radius 2 0.5m below the centre of the players head
				//(just over the floor beneath the bed)
				ServerLevel sLevel = (ServerLevel)player.level;
				sLevel.sendParticles(ParticleTypes.DRAGON_BREATH, x, y - 0.5, z, 100, 2, 0, 2, 0);
				
			}
			
			if (time == 30) {
				
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
				//this one is just more particles in a slightly smaller circle, making it less sparse.
				ServerLevel sLevel = (ServerLevel)player.level;
				sLevel.sendParticles(ParticleTypes.DRAGON_BREATH, x, y - 0.5, z, 800, 1.5, 0, 1.5, 0);
				
			}
			
			if (time == 45) {
				
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
				//another non-sparse circle, this time raised up around the player
				ServerLevel sLevel = (ServerLevel)player.level;
				sLevel.sendParticles(ParticleTypes.DRAGON_BREATH, x, y, z, 200, 0.5, 0, 0.5, 0);
			}
			
			if (time == 55) {
				//when night vision is about to expire, it creates a flashing effect. Combined with blindness the thematic effect is really cool imo.
				//we only need these effects to last 44 ticks until the player dies, but having it persist after they exit the bed if they escape seemed pretty cool.
				player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0, false, false));
				player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 90, 0, false, false));
				
			}
			
			if (time == 75) {
				//there's no (to my knowledge) in built method to tell the client to play a sound, so we use a SimpleChannel to convey that it ought to.
				ServerPlayer sPlayer = (ServerPlayer)player;
				EnderDreamPacketHandler.INSTANCE.sendTo(new PacketDragonRoar(), sPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			}
			
			if (time == 99) {
				//if the player didn't get the hint that something bad was about to happen, they die
				player.hurt(EnderDream.DRAGON_MENTAL, 1000);
				//for some reason if the player dies on the 2nd to last tick of sleeping they can still skip the night, which is exactly what we're trying to avoid.
				//so we just shove their corpse out the bed.
				//interestingly .stopSleeping() is also called inside of .die(DamageSource dmgSrc), so I have no idea why we have to do it again.
				player.stopSleeping();
				
			}
		}
	}
}
