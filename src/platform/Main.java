package platform;

import audio.AudioCommands;
import game.Game;
import graphics.DrawInfo;
import graphics.Renderer;

import java.awt.*;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import audio.AudioPlayer;
import graphics.Texture;
import graphics.commands.DrawCommand;

public class Main {
    private static int drawHeight;

    public static void main(String[] args) throws InterruptedException {
        final int drawWidth = 512;
        final int drawHeight = 512;

        WindowManager windowManager = new WindowManager("my first window", drawWidth, drawHeight);
        windowManager.createWindow();

        Renderer renderer = new Renderer();
        renderer.loadImage(Texture.PIG, new File("res/textures/test.png"));

        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.loadFile(new File("res/audio/test.wav"));

        DrawInfo drawInfo = new DrawInfo();
        drawInfo.width = drawWidth;
        drawInfo.height = drawHeight;

        Game game = new Game();
        Input input = new Input();
        Queue<DrawCommand> drawCommands = new ArrayDeque<>();
        AudioCommands audioCommands = new AudioCommands();

        final float targetFps = 60.0f;
        final float targetSecondsPerFrame = 1.0f / targetFps;
        final long targetNanoSecondsPerFrame = 16_666_667;
        float fps = targetFps;
        float secondsPerFrame = targetSecondsPerFrame;

        audioPlayer.start();
        long frameEndTime = System.nanoTime();

        Graphics2D g = windowManager.createGraphics();
        // THE MAIN LOOP
        while (windowManager.getIsWindowAlive()) {
            long frameStartTime = frameEndTime;

            windowManager.flip();

//            System.out.println("\033[0;33mSeconds per frame: " + secondsPerFrame);
//            System.out.println("\033[0;31mFrames per second: " + fps);

            windowManager.updateInput(input);

            game.update(input, drawCommands, audioCommands);

            renderer.draw(g, drawCommands);
            audioPlayer.writeAudioStream();

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
