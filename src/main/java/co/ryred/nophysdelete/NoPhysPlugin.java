package co.ryred.nophysdelete;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Cory Redmond on 11/03/2017.
 * ace@ac3-servers.eu
 */
public class NoPhysPlugin extends JavaPlugin implements Listener {

    private boolean addMode;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if( event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR || event.getPlayer().getItemInHand().getType() != Material.BLAZE_ROD)
            return;

        if( event.getPlayer().hasPermission("ccplugin.admin") && addMode ) {
            event.getClickedBlock().setTypeIdAndData(0, (byte)0, false);
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player) || !sender.hasPermission("ccplugin.admin")) {
            sender.sendMessage(ChatColor.RED + "Only players with permissions can do this command!");
            return true;
        }

        addMode = !addMode;
        String enabled = addMode ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled" ;
        sender.sendMessage(ChatColor.YELLOW + "Add mode is " + enabled + ChatColor.YELLOW + ".");
        return true;

    }

}
