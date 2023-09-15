package fr.jason.buildbattle;

import fr.jason.buildbattle.listeners.PlayerEventListener;
import fr.jason.buildbattle.listeners.TeamGuiListener;
import fr.jason.buildbattle.managers.BTeamManager;
import fr.jason.buildbattle.managers.PlayerDataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private BState state;
    private List<Player> players = new ArrayList<>();
    private PlayerDataManager playerDataManager;
    private BTeamManager teamManager;

    @Override
    public void onEnable() {

        setState(BState.WAITING);

        playerDataManager = new PlayerDataManager();
        teamManager = new BTeamManager();

        initializeTeams();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerEventListener(this), this);
        pm.registerEvents(new TeamGuiListener(), this);

    }

    private void initializeTeams(){
        BTeam red = teamManager.createTeam("Red", ChatColor.RED);
        BTeam blue = teamManager.createTeam("Blue", ChatColor.BLUE);
        BTeam yellow = teamManager.createTeam("Yellow", ChatColor.YELLOW);
        BTeam green = teamManager.createTeam("Green", ChatColor.GREEN);
    }

    public void setState(BState state){
        this.state = state;
    }

    public boolean isState(BState state){
        return this.state == state;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }
    public BTeamManager getTeamManager(){
        return teamManager;
    }
}
