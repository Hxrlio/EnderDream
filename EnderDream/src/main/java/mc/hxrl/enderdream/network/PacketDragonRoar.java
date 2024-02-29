package mc.hxrl.enderdream.network;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class PacketDragonRoar {
	
	public PacketDragonRoar() {
		
	}
	
	public void encoder(FriendlyByteBuf buffer) {
		
	}
	
	public PacketDragonRoar(FriendlyByteBuf buffer) {
		
	}
	
	public void messageConsumer(Supplier<NetworkEvent.Context> ctx) {
		
	}
	
	public static void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() ->
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleSafe(ctx))
		);
		ctx.get().setPacketHandled(true);
	}
	
	public static void handleSafe(Supplier<NetworkEvent.Context> ctx) {
		Minecraft mc = Minecraft.getInstance();
		mc.player.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ender_dragon.growl")), 1, (float)0.7);
	}
}
