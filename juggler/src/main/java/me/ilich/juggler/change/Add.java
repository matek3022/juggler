package me.ilich.juggler.change;

import android.content.Intent;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public final class Add {

    public static Add.Interface none() {
        return new NoneAdd();
    }

    public static Add.Interface deeper(State state) {
        return new DeeperAdd(state, null);
    }

    public static Add.Interface deeper(State state, String tag, TargetBound... targetBounds) {
        return new DeeperAdd(state, tag, targetBounds);
    }

    public static Add.Interface deeper(State state, TargetBound... targetBounds) {
        return new DeeperAdd(state, null, targetBounds);
    }

    public static Add.Interface linear(State state) {
        return new LinearAdd(state, null);
    }

    public static Add.Interface linear(State state, String tag, TargetBound... targetBounds) {
        return new LinearAdd(state, tag, targetBounds);
    }

    public static Add.Interface linear(State state, TargetBound... targetBounds) {
        return new LinearAdd(state, null, targetBounds);
    }

    public static Interface newActivity(State state) {
        return new NewActivityAdd(state);
    }

    public static Interface newActivity(State state, Class<? extends JugglerActivity> activityClass) {
        return new NewActivityAdd(state, activityClass);
    }

    private Add() {

    }

    public interface Interface {

        Item add(JugglerActivity activity, Stack<Item> items, Intent intent);

    }

}
