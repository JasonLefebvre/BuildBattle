package fr.jason.buildbattle.tasks;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import fr.jason.buildbattle.BState;
import fr.jason.buildbattle.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoStart extends BukkitRunnable {

    private int timer = 10;

    private Main main;

    public AutoStart(Main main){
        this.main = main;
    }

    @Override
    public void run() {

        // Mise a jour de la barre d'exp en fonction du timer
        for (Player pls : main.getPlayers()){
            pls.setLevel(timer);
        }

        // Si le nombre de joueurs connectés est inférieur à 2, la queue s'arrête
        if (main.getPlayers().size() < 2){
            cancel();
        }


        // Compte à rebours pour le début de la partie
        if (timer == 5 || timer == 3 || timer == 2 || timer == 1) {
            Bukkit.broadcastMessage("§a§lDébut de la partie dans : §l§6" + timer + "§a§l s");
        }

        // Début de la partie
        if (timer == 0){

            Bukkit.broadcastMessage("§e§lLancement du Buildbattle");
            main.setState(BState.PLAYING);

            // gestion de maps
            MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
            MVWorldManager worldManager = core.getMVWorldManager();

            /*
            Génération des maps par team
            paramétrer les bordures de map
            téléportation des teams
            donner la hache
            .sendTitle("§k§l| §eC'est parti ! §f§k§l|", "§9§nBon courage"))
            son : .playNote(teamWorld.getSpawnLocation(), Instrument.IRON_XYLOPHONE, Note.natural(1, Note.Tone.A)))
             */

        }

        GameCycle cycle = new GameCycle(main);
        cycle.runTaskTimer(main, 0, 20);
        cancel();

        timer--;
    }
}
