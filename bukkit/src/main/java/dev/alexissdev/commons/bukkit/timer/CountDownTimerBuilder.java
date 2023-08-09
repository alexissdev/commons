package dev.alexissdev.commons.bukkit.timer;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class CountDownTimerBuilder {

    private Plugin plugin;
    private int seconds;
    private Consumer<CountDownTimer> everySecond;
    private Runnable beforeTimer;
    private Runnable afterTimer;

    public CountDownTimerBuilder() {
        this.plugin = null;
        this.seconds = -1;
        this.everySecond = ignored -> {};
        this.beforeTimer = () -> {};
        this.afterTimer = () -> {};
    }

    /**
     * @param plugin The plugin that will be used to schedule the timer
     * @return The builder instance
     */

    public CountDownTimerBuilder plugin(@NotNull Plugin plugin) {
        this.plugin = plugin;
        return this;
    }

    /**
     * @param seconds The amount of seconds the timer will run for
     * @return The builder instance
     */

    public CountDownTimerBuilder seconds(int seconds) {
        this.seconds = seconds;
        return this;
    }

    /**
     * @param everySecond The consumer that will be called every second
     * @return The builder instance
     */

    public CountDownTimerBuilder everySecond(@NotNull Consumer<CountDownTimer> everySecond) {
        this.everySecond = everySecond;
        return this;
    }

    /**
     * @param beforeTimer The runnable that will be called before the timer starts
     * @return The builder instance
     */

    public CountDownTimerBuilder beforeTimer(@NotNull Runnable beforeTimer) {
        this.beforeTimer = beforeTimer;
        return this;
    }

    /**
     * @param afterTimer The runnable that will be called after the timer ends
     * @return The builder instance
     */

    public CountDownTimerBuilder afterTimer(@NotNull Runnable afterTimer) {
        this.afterTimer = afterTimer;
        return this;
    }

    /**
     * Schedules the timer
     */

    public void scheduleTimer() {
        build().scheduleTimer();
    }

    /**
     * @return The built {@link CountDownTimer}
     */

    public CountDownTimer build() {
        if (plugin == null || seconds == -1) {
            throw new IllegalStateException("Plugin and seconds must be set");
        }

        return new CountDownTimer(plugin, seconds, beforeTimer, everySecond, afterTimer);
    }
}
