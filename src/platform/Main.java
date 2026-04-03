package platform;

import game.Game;
import audio.Sound;
import audio.AudioPlayer;
import audio.commands.AudioCommand;
import game.ui.TextButton;
import graphics.DrawInfo;
import graphics.Renderer;
import graphics.Texture;
import graphics.commands.DrawCommand;

import java.awt.*;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;


public class Main {
    private static int drawHeight;

    public static void main(String[] args) throws InterruptedException {
        final int drawWidth = 1000;
        final int drawHeight = 1000;

        Renderer renderer = new Renderer(drawWidth, drawHeight);
        renderer.loadImage(Texture.PIG, new File("res/textures/test.png"));
        renderer.loadImage(Texture.MAIN_LOGO, new File("res/textures/cave_logo_2.png"));
        renderer.loadImage(Texture.MENU_BACKGROUND, new File("res/textures/cave.png"));

        renderer.loadImage(Texture.MAIN_LOGO_FLASH_0, new File("res/textures/logo_flash_1.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_1, new File("res/textures/logo_flash_2.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_2, new File("res/textures/logo_flash_3.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_3, new File("res/textures/logo_flash_4.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_4, new File("res/textures/logo_flash_5.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_5, new File("res/textures/logo_flash_6.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_6, new File("res/textures/logo_flash_7.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_7, new File("res/textures/logo_flash_8.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_8, new File("res/textures/logo_flash_9.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_9, new File("res/textures/logo_flash_10.png"));
        renderer.loadImage(Texture.MAIN_LOGO_FLASH_10, new File("res/textures/logo_flash_11.png"));

        InputListener inputListener = new InputListener(drawWidth, drawHeight);

        WindowManager windowManager = new WindowManager("project_neo", inputListener);
        windowManager.createWindow();

        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.loadFile(Sound.MAIN_THEME, new File("res/audio/test.wav"));
        audioPlayer.loadFile(Sound.CLICK, new File("res/audio/click.wav"));
        audioPlayer.loadFile(Sound.HOVER, new File("res/audio/hover.wav"));

        DrawInfo drawInfo = new DrawInfo();
        drawInfo.width = drawWidth;
        drawInfo.height = drawHeight;

        Game game = new Game();
        Input input = new Input();
        Queue<DrawCommand> drawCommands = new ArrayDeque<>();
        Queue<AudioCommand> audioCommands = new ArrayDeque<>();

        final float targetFps = 60.0f;
        final float targetSecondsPerFrame = 1.0f / targetFps;
        final long targetNanoSecondsPerFrame = 16_666_667;
        float fps = targetFps;
        float secondsPerFrame = targetSecondsPerFrame;

        audioPlayer.start();
        long frameEndTime = System.nanoTime();

        // THE MAIN LOOP
        while (windowManager.getIsWindowAlive()) {
            long frameStartTime = frameEndTime;

            windowManager.flip(renderer.getBuffer());

            System.out.println("\033[0;33mSeconds per frame: " + secondsPerFrame);
            System.out.println("\033[0;31mFrames per second: " + fps);

            input.setSecondsPerFrame(secondsPerFrame);
            windowManager.updateInput(input);

            game.update(input, drawCommands, audioCommands);

            renderer.draw(drawCommands);
            audioPlayer.writeAudio(audioCommands);

            windowManager.setWindowAlive(input.getUiInput().getExitButton().getState() != TextButton.ButtonState.PRESSED);

            frameEndTime = System.nanoTime();
            long elapsedTime = frameEndTime - frameStartTime;
            long sleepTimeNanoSeconds = targetNanoSecondsPerFrame - elapsedTime;
            long sleepTimeMilliSeconds = sleepTimeNanoSeconds / 1_000_000 - 8;

            if (sleepTimeMilliSeconds > 0) {
                Thread.sleep(sleepTimeMilliSeconds);
            }

            boolean overslept = true;

            frameEndTime = System.nanoTime();
            while (frameEndTime - frameStartTime < targetNanoSecondsPerFrame) {
                frameEndTime = System.nanoTime();
                overslept = false;
            }

            if (overslept) {
                System.out.println("\033[0;36myou overslept");
            }

            secondsPerFrame = (frameEndTime - frameStartTime) / 1_000_000_000.0f;
            fps = 1.0f / secondsPerFrame;
        }

        windowManager.destroyWindow();
    }
}
