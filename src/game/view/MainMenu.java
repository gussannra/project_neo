package game.view;

import audio.commands.AudioCommand;
import game.Animation;
import game.EaseFunction;
import game.ui.Sprite;
import graphics.Texture;
import graphics.commands.DrawCommand;
import graphics.commands.DrawImageCommand;
import platform.Input;
import platform.UiInput;

import java.util.Queue;

public class MainMenu implements View {
    Sprite logoSprite;
    Sprite flashSprite;

    public MainMenu() {
        Animation animation = new Animation(0.08f, 0.14f, 3f, EaseFunction.IN_OUT_QUAD);
        animation.setPingPong(true);
        logoSprite = new Sprite(0f, 0f, 1f, 0.2f, Texture.MAIN_LOGO);
        logoSprite.setYAnimation(animation);

        final int numFlashAnimationFrames = 11;
        Animation flashAnimation = new Animation(0, numFlashAnimationFrames - 0.001f, 0.5f, EaseFunction.LINEAR);
        flashSprite = new Sprite(logoSprite.getX(), logoSprite.getY(), logoSprite.getW(), logoSprite.getH(), Texture.MAIN_LOGO_FLASH_0);

        for (Texture texture : Texture.values()) {
            if (texture.name().startsWith("MAIN_LOGO_FLASH_") && !texture.name().startsWith("MAIN_LOGO_FLASH_0")) {
                flashSprite.addTexture(texture);
            }
        }

        flashSprite.setTextureIndexAnimation(flashAnimation);
        flashSprite.setYAnimation(animation);

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
        flashSprite.update(input, outDraw, outAudio);
    }
}
