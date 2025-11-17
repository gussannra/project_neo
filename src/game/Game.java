package game;

import audio.Sound;
import audio.commands.AudioCommand;
import audio.commands.PlayAudioCommand;
import game.ui.TextButton;
import game.view.MainMenu;
import game.view.View;
import game.view.ViewState;
import graphics.TextFont;
import graphics.commands.DrawCommand;
import platform.Input;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Game {
    Map<ViewState, View> viewMap;
    ViewState currentViewState;

    public Game() {
        viewMap = new HashMap<>();
        viewMap.put(ViewState.MAIN_MENU, new MainMenu());
        currentViewState = ViewState.MAIN_MENU;
    }

    public void update(final Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        viewMap.get(currentViewState).update(input, outDraw, outAudio);
    }
}
