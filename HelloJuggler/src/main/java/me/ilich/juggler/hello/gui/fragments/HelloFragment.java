package me.ilich.juggler.hello.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.LoginState;

public class HelloFragment extends JugglerFragment {

    public static HelloFragment create() {
        return new HelloFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Remove.all(), Add.deeper(new LoginState()));
            }
        });
    }

}
