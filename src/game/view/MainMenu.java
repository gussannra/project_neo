package game.view;

import audio.commands.AudioCommand;
import game.Animation;
import game.EaseFunction;
import game.ui.Sprite;
import graphics.AlignmentX;
import graphics.Texture;
import graphics.commands.DrawCommand;
import graphics.commands.DrawImageCommand;
import platform.Input;
import platform.UiInput;

import java.util.Queue;

public class MainMenu implements View {
    Sprite logoSprite;

    public MainMenu() {
        Animation animation = new Animation(0.08f, 0.14f, 3f, EaseFunction.IN_OUT_QUAD);

        logoSprite = new Sprite(0f, 0f, 1f, 0.2f, Texture.MAIN_LOGO);

        logoSprite.setYAnimation(animation);
    }

    @Override
    public void update(final Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        DrawImageCommand drawBackgroundCommand = new DrawImageCommand(0f, 0f, 1f, 1f, Texture.MENU_BACKGROUND);
        outDraw.add(drawBackgroundCommand);

        UiInput uiInput = input.getUiInput();
        uiInput.getStartButton().update(input, outDraw, outAudio);
        uiInput.getOptionsButton().update(input, outDraw, outAudio);
        uiInput.getExitButton().update(input, outDraw, outAudio);

        logoSprite.update(input, outDraw, outAudio);
    }
}
