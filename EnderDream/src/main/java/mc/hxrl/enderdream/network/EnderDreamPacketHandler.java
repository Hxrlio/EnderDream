package mc.hxrl.enderdream.network;

import mc.hxrl.enderdream.EnderDream;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class EnderDreamPacketHandler {
	
	private static final String PROTOCOL_VERSION = "1";
	private static int pId = 0;
	private static int id() {
		return pId++;
	}
	
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(EnderDream.MODID, "sound"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
}
