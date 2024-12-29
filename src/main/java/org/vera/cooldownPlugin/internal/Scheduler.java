package org.vera.cooldownPlugin.internal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scheduler {
    public static void execute() {
        HashMap<String, CopyOnWriteArrayList<HashMap<String, String>>> players = Cooldown.getMarkedPlayers();

        for (String userId : players.keySet()) {
            CopyOnWriteArrayList<HashMap<String, String>> materialsList = players.get(userId);

            for (HashMap<String, String> info : materialsList) {
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                try {
                    Date finishDate = formatter.parse(info.get("date"));

                    if (finishDate.before(new Date()) || finishDate.equals(new Date())) {
                        Cooldown.removeMaterialFromCooldown(userId, info.get("material"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
