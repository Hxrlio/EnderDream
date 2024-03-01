package mc.hxrl.enderdream;

import com.mojang.logging.LogUtils;

import mc.hxrl.enderdream.network.EnderDreamPacketHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("enderdream")
public class EnderDream {
	
	public static final String MODID = "enderdream";
    public static final Logger LOGGER = LogUtils.getLogger();
    //custom damage source that cannot be stopped, resulting in a guaranteed player death with sufficiently high damage
    public static final DamageSource DRAGON_MENTAL = new DamageSource("enderdream.dragonmental").bypassArmor().bypassInvul().bypassMagic();

    public EnderDream() {
        
    	IEventBus mbus = FMLJavaModLoadingContext.get().getModEventBus();
        mbus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	//register SimpleChannel
    	EnderDreamPacketHandler.register();
    	
    }
}
