package mc.hxrl.enderdream.network;


import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class PacketDragonRoar {
	//the message is empty so we don't have to bother with these 3
	public PacketDragonRoar() {
		
	}
	
	public void encoder(FriendlyByteBuf buffer) {
		
	}
	
	public PacketDragonRoar(FriendlyByteBuf buffer) {
		
	}
	//handle is on the network thread, so we send it back to the game threads, then make sure its on the client thread, as thats the whole point of this packet.
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() ->
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleSafe(ctx))
		);
		ctx.get().setPacketHandled(true);
	}
	//now we're definitely on the client we can do what we need to do.
	public static void handleSafe(Supplier<NetworkEvent.Context> ctx) {
		Minecraft mc = Minecraft.getInstance();
		mc.player.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ender_dragon.growl")), 1, (float)0.8);
	}
}
