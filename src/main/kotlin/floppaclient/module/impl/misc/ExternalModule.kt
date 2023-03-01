package floppaclient.module.impl.misc

import floppaclient.FloppaClient.Companion.mc
import floppaclient.module.Category
import floppaclient.module.Module
import floppaclient.module.SelfRegisterModule
import floppaclient.module.settings.impl.BooleanSetting
import floppaclient.utils.ChatUtils.modMessage
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

/**
 * A simple example of a Module written in Kotlin.
 *
 * In Kotlin Modules are declared as objects inheriting from the [Module] class.
 * The [SelfRegisterModule] annotation is required for this Module to be registered by the ModuleManager.
 * Specify the name, category and description of your module when delegating to the [Module] constructor.
 * Keep in mind that the name of your Module has to be unique!
 *
 * You can use this as a template for your own Modules.
 * The documentation of the members of this class should help you understand what everything does.
 *
 * Refer to the [Module] documentation for more information about Modules.
 * @author Aton
 */
@SelfRegisterModule
object ExternalModule : Module(
    "External Module",
    category = Category.MISC,
    description = "An external module"
) {
    /**
     * Here a Setting is added to your Module.
     *
     * This property is defined through delegation.
     *
     * **Remember to register your Setting**. Otherwise it will not appear in the GUI.
     * In this case this is done automatically by the provider of the delegate.
     * If you do not use delegation use the register method.
     *
     *     private val mySetting = register(BooleanSetting("My Setting"))
     * Or the operator form using +
     *
     *     private val mySetting = +BooleanSetting("My Setting")
     * You can remove or replace this Setting if you don't need it.
     */
    private val mySetting: Boolean by BooleanSetting("My Setting")

    /**
     * An Event listener for your Module.
     *
     * Methods annotated with [SubscribeEvent] are event listeners.
     * Such a method listens for the event specified by the Type of the single parameter of the method.
     * In this case that event is the Forge [ClientChatReceivedEvent].
     * Whenever that event occurs Forge will invoke this method and the code will be run.
     *
     * The event listeners in your Module will only be active when the Module is enabled.
     * You can make them always active by annotation the class with [AlwaysActive][floppaclient.module.AlwaysActive].
     */
    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (!mySetting || mc.thePlayer == null) return
        modMessage("Received chat message")
    }
}