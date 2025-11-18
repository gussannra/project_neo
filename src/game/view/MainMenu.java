package game.view;

import audio.commands.AudioCommand;
import game.Animation;
import game.EaseFunction;
import game.EasingFunction;
import game.ui.Sprite;
import graphics.Alignment;
import graphics.Texture;
import graphics.commands.DrawCommand;
import platform.Input;
import platform.UiInput;

import java.util.Queue;

public class MainMenu implements View {
    Sprite logoSprite;

    public MainMenu() {
        Animation animation = new Animation(0.08f, 0.14f, 0.1f, EaseFunction.IN_OUT_QUAD);

        logoSprite = new Sprite(0.5f, 0.1f, 0.5f, 0.2f, Texture.MAIN_LOGO, Alignment.CENTER, animation);
    }

    @Override
    public void update(final Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        UiInput uiInput = input.getUiInput();
        uiInput.getStartButton().update(input, outDraw, outAudio);
        uiInput.getOptionsButton().update(input, outDraw, outAudio);
        uiInput.getExitButton().update(input, outDraw, outAudio);

        logoSprite.update(input, outDraw, outAudio);
    }
}
