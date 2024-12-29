package org.vera.cooldownPlugin.internal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * This class manipulates the cooldown history.
 * Structure of the cooldonw:
 * {
 * "user_id": [{
 * "date": "date string",
 * "material": "material string" }]
 * }
 *
 * @author Vera0011
 */
public class Cooldown {
    private static final HashMap<String, ArrayList<HashMap<String, String>>> markedPlayers = new HashMap<>();

    public static HashMap<String, ArrayList<HashMap<String, String>>> getMarkedPlayers() {
        return markedPlayers;
    }

    /**
     * Adds a user to the cooldown
     */
    public static boolean addUserToCooldown(String userId, Date newDate, Material material) {
        HashMap<String, String> customInfo = new HashMap<>();

        customInfo.put("date", newDate.toString());
        customInfo.put("material", material.toString());

        if (markedPlayers.containsKey(userId)) {
            markedPlayers.get(userId).add(customInfo);
        } else {
            ArrayList<HashMap<String, String>> itemsList = new ArrayList<>();
            itemsList.add(customInfo);
            markedPlayers.put(userId, itemsList);
        }

        System.out.println("Added user " + userId + " to cooldown");

        return true;
    }

    /**
     * Removes a user from the cooldown
     *
     * @return true if the deletion was successfully
     */
    public static boolean removeUserFromCooldown(String userId) {
        if (markedPlayers.get(userId) != null) {
            markedPlayers.remove(userId);
            System.out.println("Removed user " + userId + " from cooldown");
            return true;
        } else {
            System.out.println("No user removed");
            return false;
        }
    }

    public static boolean cleanCooldown() {
        markedPlayers.clear();
        System.out.println("Cooldown cleaned");
        return true;
    }

    /**
     * Verifies if a specific user (name) has an active cooldown for a specific item
     * @param userName The name of the user
     * @param material The material name
     * @return "true" if the user has an active cooldown for that specific item
     */
    public static String hasCooldown(String userName, String material) {
        Player player = Bukkit.getPlayer(userName);

        if (player != null) {
            String userId = player.getUniqueId().toString();

            if (markedPlayers.containsKey(userId)) {
                for (HashMap<String, String> customInfo : markedPlayers.get(userId)) {
                    if (customInfo.get("material").equals(material.toUpperCase())) {
                        return "true";
                    }
                }
            }
        }

        return "false";
    }
}
