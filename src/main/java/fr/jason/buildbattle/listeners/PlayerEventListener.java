package fr.jason.buildbattle.listeners;

import fr.jason.buildbattle.BState;
import fr.jason.buildbattle.BTeam;
import fr.jason.buildbattle.Main;
import fr.jason.buildbattle.managers.PlayerData;
import fr.jason.buildbattle.tasks.AutoStart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Objects;

public class PlayerEventListener implements Listener {

    private Main plugin;

    public PlayerEventListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData();
        Location spawn = new Location(Bukkit.getWorld("world"), 1, 85, 2, 0f, 0f);

        // initialisation des informations pour le joueur
        plugin.getPlayerDataManager().setPlayerData(player, playerData);

        //boussole de choix d'equipeD
        ItemStack teams = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = teams.getItemMeta();

        meta.setDisplayName("§6Choix de team");
        meta.setLore(Collections.singletonList("§eClic-droit pour choisir ta team"));
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        teams.setItemMeta(meta);

        // verification etat de la partie

        if (plugin.isState(BState.WAITING)){
            player.teleport(spawn);
            player.setFoodLevel(20);
            player.setHealth(20);
            player.getInventory().clear();
            player.getInventory().setItem(4, teams);
        } else {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("§k| §cLe BuildBattle a déjà commencé ):§k |");
        }

        if (!plugin.getPlayers().contains(player)){
            plugin.getPlayers().add(player);
        }
        player.setGameMode(GameMode.ADVENTURE);
        event.setJoinMessage("§7[§9Build Battle Geant§7] §6" + player.getName() + " §aa rejoint la partie §8<§2" + plugin.getPlayers().size() + "§7/§2" + Bukkit.getMaxPlayers() + "§8>");

        if (plugin.isState(BState.WAITING) && plugin.getPlayers().size() >= 2){
            AutoStart start = new AutoStart(plugin);
            start.runTaskTimer(plugin, 0, 20);
            plugin.setState(BState.STARTING);
        }

    }

    Inventory inv = new TeamGuiListener().TeamGUI();

    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack current = event.getItem();

        if (current == null) return;

        if (current.getType() == Material.COMPASS && Objects.equals(current.getItemMeta().getDisplayName(), "§6Choix de team")){

            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){

                player.openInventory(inv);

            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        Inventory invCurr = event.getInventory();
        ItemStack clickItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        if (clickItem == null) return;

        if (invCurr.equals(inv)){

            event.setCancelled(true);
            player.closeInventory();

            switch (clickItem.getType()){

                case RED_WOOL:
                    BTeam red = plugin.getTeamManager().getTeam("Red");

                    if (red != null){
                        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);

                        if (playerData != null && playerData.getTeam() == null){
                            red.addMember(player);
                            playerData.setTeam(red);
                            player.sendMessage("Vous êtes dans la team §cRouge");
                            player.closeInventory();
                        } else {
                            player.sendMessage("Vous êtes déjà dans une équipe ou vous n'avez pas les droits");
                        }
                    }
                    break;

                case BLUE_WOOL:
                    BTeam blue = plugin.getTeamManager().getTeam("Blue");

                    if (blue != null){
                        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);

                        if (playerData != null && playerData.getTeam() == null){
                            blue.addMember(player);
                            playerData.setTeam(blue);
                            player.sendMessage("Vous êtes dans la team §9Bleue");
                            player.closeInventory();
                        } else {
                            player.sendMessage("Vous êtes déjà dans une équipe ou vous n'avez pas les droits");
                        }
                    }
                    break;
                case YELLOW_WOOL:
                    BTeam yellow = plugin.getTeamManager().getTeam("Yellow");

                    if (yellow != null){
                        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);

                        if (playerData != null && playerData.getTeam() == null){
                            yellow.addMember(player);
                            playerData.setTeam(yellow);
                            player.sendMessage("Vous êtes dans la team §eJaune");
                            player.closeInventory();
                        } else {
                            player.sendMessage("Vous êtes déjà dans une équipe ou vous n'avez pas les droits");
                        }
                    }
                    break;
                case LIME_WOOL:
                    BTeam green = plugin.getTeamManager().getTeam("Green");

                    if (green != null){
                        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);

                        if (playerData != null && playerData.getTeam() == null){
                            green.addMember(player);
                            playerData.setTeam(green);
                            player.sendMessage("Vous êtes dans la team §aVerte");
                            player.closeInventory();
                        } else {
                            player.sendMessage("Vous êtes déjà dans une équipe ou vous n'avez pas les droits");
                        }
                    }
            }

        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        if (event.getItemDrop().getItemStack().getType() == Material.COMPASS){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        plugin.getPlayerDataManager().removePlayerData(player);

        if (plugin.isState(BState.STARTING)){
            if (plugin.getPlayers().size() < 2){
                plugin.setState(BState.WAITING);
                for (Player pls : plugin.getPlayers()){
                    pls.setLevel(0);
                }
            }
        }
    }
}
