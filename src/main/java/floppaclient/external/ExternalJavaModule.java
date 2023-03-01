package floppaclient.external;

import floppaclient.FloppaClient;
import floppaclient.module.Category;
import floppaclient.module.Module;
import floppaclient.module.SelfRegisterModule;
import floppaclient.module.settings.Visibility;
import floppaclient.module.settings.impl.BooleanSetting;
import floppaclient.utils.ChatUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

/**
 * A simple example of a Module written in Java.
 * <p>
 * The {@link SelfRegisterModule} annotation is required for this Module to be registered by the ModuleManager.
 * <p>
 * You can use this as a template for your own Modules.
 * The documentation of the members of this class should help you understand what everything does.
 * <p>
 * Refer to the {@link Module} documentation for more information about Modules.
 * @author Aton
 */
@SelfRegisterModule
public class ExternalJavaModule extends Module {
    /**
     * <strong>DO NOT DELETE THIS!</strong>
     * <p>
     * This property will be used for loading in the module.
     * It has to be named <strong>INSTANCE</strong> and be initialized as an instance of {@link ExternalJavaModule this}
     * class extending {@link Module}.
     */
    @SuppressWarnings("unused")
    @NotNull
    public static final ExternalJavaModule INSTANCE = new ExternalJavaModule();

    /**
     * This private constructor is required for initializing {@link ExternalJavaModule#INSTANCE}.
     * This constructor is where you define the name, category and description of your module.
     * The name has to be unique!
     * <p>
     * There should not be any other constructors.
     * Do not delete this!
     */
    private ExternalJavaModule() {
        super("External Java Module", Category.MISC, "An external Java Module");
    }

    /**
     * Here a Setting is added to your Module.
     * <strong>Remember to register your Setting</strong> using the register method. Otherwise it will not appear in the GUI.
     * <p>
     * Delegation and using the + operator for registering your Setting are not available in Java.
     * <p>
     * You can remove or replace this Setting if you don't need it.
     */
    private final BooleanSetting mySetting = register(new BooleanSetting("My Setting", false, Visibility.VISIBLE, ""));

    /**
     * An Event listener for your Module.
     * <p>
     * Methods annotated with {@link SubscribeEvent} are event listeners.
     * Such a method listens for the event specified by the Type of the single parameter of the method.
     * In this case that event is the Forge {@link ClientChatReceivedEvent}.
     * Whenever that event occurs Forge will invoke this method and the code will be run.
     * <p>
     * The event listeners in your Module will only be active when the Module is enabled.
     * You can make them always active by annotation the class with {@link floppaclient.module.AlwaysActive AlwaysActive}.
     */
    @SubscribeEvent
    public final void onChat(ClientChatReceivedEvent event) {
        if (!mySetting.getEnabled() || FloppaClient.mc.thePlayer == null) return;
        ChatUtils.INSTANCE.modMessage("Received chat message", true);
    }
}
