package fr.notes.views.events;

import androidx.fragment.app.Fragment;

public class ShowFragmentEvent {

    public String fragmentName;
    public Fragment fragment = null;
    public boolean addToBackStack = true;
    public boolean replace = false;
    public Integer[] animations = null;

    public ShowFragmentEvent(Fragment fragment) {
        this.fragment = fragment;
    }
}
