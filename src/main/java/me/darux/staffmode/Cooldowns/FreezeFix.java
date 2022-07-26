package me.darux.staffmode.Cooldowns;




import me.darux.staffmode.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FreezeFix {
    private Main plugin;

    public FreezeFix(Main plugin) {
        this.plugin = plugin;
    }

    public void crearCooldown(Player jugador) {
        long millis = System.currentTimeMillis();
        FileConfiguration config = this.plugin.getConfig();
        String pathtime = "Players." + jugador.getUniqueId() + ".cooldown-recompensa";
        config.set(pathtime, millis);
        this.plugin.saveConfig();
    }

    public String getCooldown(Player jugador) {
        FileConfiguration timers = this.plugin.getConfig();
        String pathtime = "Players." + jugador.getUniqueId() + ".cooldown-recompensa";
        if (timers.contains(pathtime)) {
            String timecooldownString = timers.getString(pathtime);
            long timecooldown = Long.valueOf(timecooldownString);
            long millis = System.currentTimeMillis();
            long cooldown = 2L;
            long cooldownmil = cooldown * 1000L;
            long espera = millis - timecooldown;
            long esperaDiv = espera / 1000L;
            long esperatotalseg = cooldown - esperaDiv;
            long esperatotalmin = esperatotalseg / 60L;
            long esperatotalhour = esperatotalmin / 60L;
            if (timecooldown + cooldownmil > millis && timecooldown != 0L) {
                if (esperatotalseg > 59L) {
                    esperatotalseg -= 60L * esperatotalmin;
                }

                String time = "";
                if (esperatotalseg != 0L) {
                    time = esperatotalseg + "s";
                }

                if (esperatotalmin > 59L) {
                    esperatotalmin -= 60L * esperatotalhour;
                }

                if (esperatotalmin > 0L) {
                    time = esperatotalmin + "min " + time;
                }

                if (esperatotalhour > 0L) {
                    time = esperatotalhour + "h " + time;
                }

                return time;
            } else {
                return "-1";
            }
        } else {
            return "-1";
        }
    }
}
