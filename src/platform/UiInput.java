package platform;

import game.ui.TextButton;
import graphics.AlignmentX;
import graphics.TextFont;

public class UiInput {
    // Main menu
    private TextButton startButton;
    private TextButton optionsButton;
    private TextButton exitButton;

    public UiInput() {
        startButton = new TextButton(0.5f, 0.4f, TextFont.MENU_FONT, AlignmentX.CENTER);
        startButton.setText("Start game");
        optionsButton = new TextButton(0.5f, 0.6f, TextFont.MENU_FONT, AlignmentX.CENTER);
        optionsButton.setText("Options");
        exitButton = new TextButton(0.5f, 0.8f, TextFont.MENU_FONT, AlignmentX.CENTER);
        exitButton.setText("Exit");
    }

    public TextButton getStartButton() {
        return startButton;
    }

    public TextButton getOptionsButton() {
        return optionsButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }
}
