package site.scalarstudios.scalarutils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import site.scalarstudios.scalarutils.block.ScalarUtilsBlocks;
import site.scalarstudios.scalarutils.item.ScalarUtilsCreativeTabs;
import site.scalarstudios.scalarutils.item.ScalarUtilsItems;

@Mod(ScalarUtils.MODID)
public class ScalarUtils {
    public static final String MODID = "scalarutils";

    public ScalarUtils(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Register Items and Blocks
        ScalarUtilsItems.register(modEventBus);
        ScalarUtilsBlocks.register(modEventBus);

        // Register Creative Tabs
        ScalarUtilsCreativeTabs.register(modEventBus);
        modEventBus.addListener(ScalarUtilsCreativeTabs::registerTabs);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}
