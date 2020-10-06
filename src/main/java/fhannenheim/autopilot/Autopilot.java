package fhannenheim.autopilot;

import fhannenheim.autopilot.chat.ChatCommandHandler;
import fhannenheim.autopilot.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("autopilot")
@Mod.EventBusSubscriber(modid = Autopilot.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Autopilot {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "autopilot";

    public Autopilot() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CONFIG);
        Config.loadConfig(Config.CONFIG, FMLPaths.CONFIGDIR.get().resolve("autopilot-client.toml").toString());

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ChatCommandHandler());
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        FlightHandler handler = new FlightHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        handler.onClientSetup();
    }

}
