package mc.hxrl.enderdream;

import com.mojang.logging.LogUtils;

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

    public EnderDream() {
        
    	IEventBus mbus = FMLJavaModLoadingContext.get().getModEventBus();
        mbus.addListener(this::setup);
//        mbus.addListener(this::enqueueIMC);
//        mbus.addListener(this::processIMC);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	
    	LOGGER.info("operational");
    	
    }
    
/* I might need to do these things for the purposes of compatibility with other sleep related mods, only time will tell.
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event) {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }
*/
}
