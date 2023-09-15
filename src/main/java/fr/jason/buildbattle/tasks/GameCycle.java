package fr.jason.buildbattle.tasks;

import fr.jason.buildbattle.BState;
import fr.jason.buildbattle.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameCycle extends BukkitRunnable {

    public Main main;
    private int timer = 120;

    public GameCycle(Main main){
        this.main = main;
    }

    @Override
    public void run() {

        if (timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
            Bukkit.broadcastMessage("Il reste " + timer + "s !");
        }
        if (timer == 0){
            cancel();
            Bukkit.broadcastMessage("Fin de la partie !");
            main.setState(BState.FINISH);

            for (int i = 0; i < main.getPlayers().size(); i++){
                Player p = main.getPlayers().get(i);
                World world = main.getServer().getWorld("world");

                p.teleport(new Location(world, 0, 100, 0, 0f, 0f)); //changer les cos
            }
        }
        timer--;
    }
}
