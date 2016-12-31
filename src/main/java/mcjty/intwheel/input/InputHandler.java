package mcjty.intwheel.input;


import mcjty.intwheel.InteractionWheel;
import mcjty.intwheel.WheelSupport;
import mcjty.intwheel.api.IWheelActions;
import mcjty.lib.tools.MinecraftTools;
import mcjty.theoneprobe.proxy.GuiProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class InputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.keyOpenWheel.isPressed()) {
            EntityPlayerSP player = MinecraftTools.getPlayer(Minecraft.getMinecraft());
            RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;
            if (mouseOver != null && mouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos pos = mouseOver.getBlockPos();
                WorldClient world = MinecraftTools.getWorld(Minecraft.getMinecraft());
                IWheelActions actions = WheelSupport.getWheelActions(world, pos);
                if (actions != null) {
                    player.openGui(InteractionWheel.instance, GuiProxy.GUI_NOTE, player.getEntityWorld(), pos.getX(), pos.getY(), pos.getZ());
                }
            }
        }
    }

}