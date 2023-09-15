package fr.jason.buildbattle.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerDataManager {

    private Map<Player, PlayerData> playerDataMap;

    public PlayerDataManager(){
        this.playerDataMap = new HashMap<>();
    }

    public PlayerData getPlayerData(Player player){
        return playerDataMap.get(player);
    }

    public void setPlayerData(Player player, PlayerData playerData){
        playerDataMap.put(player, playerData);
    }

    public void removePlayerData(Player player){
        playerDataMap.remove(player);
    }

}
