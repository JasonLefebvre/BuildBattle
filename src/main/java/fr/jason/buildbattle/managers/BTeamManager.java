package fr.jason.buildbattle.managers;

import fr.jason.buildbattle.BTeam;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class BTeamManager {

    private Map<String, BTeam> teams;

    public BTeamManager(){
        this.teams = new HashMap<>();
    }

    public BTeam createTeam(String name, ChatColor color){
        BTeam team = new BTeam(name, color);
        teams.put(name, team);
        return team;
    }

    public BTeam getTeam(String name){
        return teams.get(name);
    }

    public void addPlayerToTeam(Player player, BTeam team){
        team.addMember(player);
    }

    public void removePlayerFromTeam(Player player, BTeam team){
        team.removeMember(player);
    }
}
