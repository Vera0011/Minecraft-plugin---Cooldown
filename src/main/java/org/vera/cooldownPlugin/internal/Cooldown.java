package org.vera.cooldownPlugin.internal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private static final HashMap<String, CopyOnWriteArrayList<HashMap<String, String>>> markedPlayers = new HashMap<>();

    public static HashMap<String, CopyOnWriteArrayList<HashMap<String, String>>> getMarkedPlayers() {
        return markedPlayers;
    }

    /**
     * Adds a user to the cooldown
     */
    public static synchronized boolean addUserToCooldown(String userId, Date newDate, Material material) {
        HashMap<String, String> customInfo = new HashMap<>();

        customInfo.put("date", newDate.toString());
        customInfo.put("material", material.toString());

        if (markedPlayers.containsKey(userId)) {
            markedPlayers.get(userId).add(customInfo);
        } else {
            CopyOnWriteArrayList<HashMap<String, String>> itemsList = new CopyOnWriteArrayList<>();
            itemsList.add(customInfo);
            markedPlayers.put(userId, itemsList);
        }

        return true;
    }

    /**
     * Removes a user from the cooldown
     *
     * @return true if the deletion was successfully
     */
    public static synchronized boolean removeUserFromCooldown(String userId) {
        if (markedPlayers.get(userId) != null) {
            markedPlayers.remove(userId);
            return true;
        } else {
            return false;
        }
    }

    public static synchronized void removeMaterialFromCooldown(String userId, String material) {
        if (markedPlayers.get(userId) != null) {
            CopyOnWriteArrayList<HashMap<String, String>> itemsList = markedPlayers.get(userId);

            ArrayList<HashMap<String, String>> itemsToRemove = new ArrayList<>();

            for (HashMap<String, String> item : itemsList) {
                if (item.get("material").equals(material)) {
                    itemsToRemove.add(item);
                }
            }

            itemsList.removeAll(itemsToRemove);

            if (itemsList.isEmpty()) {
                markedPlayers.remove(userId);
            }
        }
    }

    public static synchronized boolean cleanCooldown() {
        markedPlayers.clear();
        return true;
    }

    /**
     * Verifies if a specific user (name) has an active cooldown for a specific item
     *
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
