package org.vera.cooldownPlugin.internal;

import org.bukkit.Material;

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
}
