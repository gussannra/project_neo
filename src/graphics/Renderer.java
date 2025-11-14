package graphics;

import graphics.commands.DrawCommand;
import graphics.commands.DrawCommandVisitor;
import graphics.commands.DrawImageCommand;
import graphics.commands.DrawTextCommand;

import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer implements DrawCommandVisitor {
    private Graphics2D g;
    private Map<Texture, BufferedImage> textureMap;
    private Map<TextFont, Font> fontMap;
    private Map<TextColor, Color> colorMap;

    public Renderer() {
        textureMap = new HashMap<>();
        fontMap = new HashMap<>();
        colorMap = new HashMap<>();

        for (TextFont font : TextFont.values()) {
            fontMap.put(font, new Font(font.fontName, Font.PLAIN, font.size));
        }

        for (TextColor color : TextColor.values()) {
            colorMap.put(color, new Color(color.r, color.g, color.b));
        }
    }

    public void draw(Graphics2D g, Queue<DrawCommand> drawCommands) {
        this.g = g;
        clear(g);
        while (!drawCommands.isEmpty()) {
            drawCommands.poll().accept(this);
        }
    }

    private void clear(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 512, 512); // TODO: do not hardcode.
    }

    private void clear(int[] outPixels) {
        Arrays.fill(outPixels, 0);
    }

    public void loadImage(Texture texture, File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            textureMap.put(texture, bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void drawImage(int[] outPixels, DrawInfo drawInfo, int x, int y) {
//        int sourceX = Math.max(-x, 0);
//        int sourceY = Math.max(-y, 0);
//
//        int x2 = x + image.getWidth() - drawInfo.width;
//        int y2 = y + image.getHeight() - drawInfo.height;
//
//        int sourceWidth = image.getWidth() + Math.min(-x2, 0);
//        int sourceHeight = image.getHeight() + Math.min(-y2, 0);
//        int destinationIndex = x + y * drawInfo.width;
//        image.getRGB(sourceX, sourceY, sourceWidth, sourceHeight, outPixels, destinationIndex, drawInfo.width);
//    }

    public void drawRect(int[] outPixels, DrawInfo drawInfo) {
        int w = 50;
        int h = 50;
        int mx = drawInfo.mousePoint.x;
        int my = drawInfo.mousePoint.y;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int offsetY = y - h / 2;
                int offsetX = x - w / 2;
                int drawX = mx + offsetX;
                int drawY = my + offsetY;
                int index = drawX + drawY * 512;

                if (index < 0 || index >= outPixels.length) {
                    continue;
                }

                if (drawX >= drawInfo.width || drawX < 0) {
                    continue;
                }

                outPixels[index] = 0x00FF00;
            }

        }
    }

    private int convertToBufferPixels(float normalized) {
        return (int) (normalized * 512);
    }

    @Override
    public void visit(DrawImageCommand cmd) {
        int x = convertToBufferPixels(cmd.x());
        int y = convertToBufferPixels(cmd.y());
        BufferedImage img = textureMap.get(cmd.texture());

        g.drawImage(img, x, y, null);
    }

    @Override
    public void visit(DrawTextCommand cmd) {
        int x = convertToBufferPixels(cmd.x());
        int y = convertToBufferPixels(cmd.y());
        g.setColor(colorMap.get(cmd.color()));
        g.setFont(fontMap.get(cmd.font()));
        g.drawString(cmd.text(), x, y);
    }
}
