package fr.jason.buildbattle;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BTeam {

    private String name;
    private ChatColor color;
    private List<Player> members;

    public BTeam(String name, ChatColor color){
        this.name = name;
        this.color = color;
        this.members = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public ChatColor getColor(){
        return color;
    }

    public List<Player> getMembers(){
        return members;
    }

    public void addMember(Player player){
        members.add(player);
    }

    public void removeMember(Player player){
        members.remove(player);
    }

}
