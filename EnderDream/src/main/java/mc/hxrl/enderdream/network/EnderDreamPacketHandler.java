package mc.hxrl.enderdream.network;

import mc.hxrl.enderdream.EnderDream;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class EnderDreamPacketHandler {
	//set a version because it wants to check if it matches, even though it always should because the mod itself wants to match version
	private static final String PROTOCOL_VERSION = "1";
	//id starts at 0
	private static int pId = 0;
	//and increments over time
	private static int id() {
		return pId++;
	}
	public static SimpleChannel INSTANCE;
	public static void register() {
		//create a simple channel
		SimpleChannel net = NetworkRegistry.newSimpleChannel(
				new ResourceLocation(EnderDream.MODID, "sound"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
		INSTANCE = net;
		//and register the "PacketDragonRoar" message to it
		net.registerMessage(id(), PacketDragonRoar.class, PacketDragonRoar::encoder, PacketDragonRoar::new, PacketDragonRoar::handle);
	}
	
}
