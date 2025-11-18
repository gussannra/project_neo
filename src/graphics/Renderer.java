package graphics;

import game.ui.TextButton;
import graphics.commands.*;

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
    private BufferedImage buffer;

    public Renderer(int width, int height) {
        textureMap = new HashMap<>();
        fontMap = new HashMap<>();
        colorMap = new HashMap<>();
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = buffer.createGraphics();
        g.setBackground(Color.BLACK);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for (TextFont font : TextFont.values()) {
            int size = (int) (font.size * width * 1.2f); // TODO: don't hardcode.
            Font awtFont = new Font(font.fontName, Font.PLAIN, size);
            fontMap.put(font, awtFont);
            FontMetrics metrics = g.getFontMetrics(awtFont);
            font.metrics = text -> (float) metrics.stringWidth(text) / buffer.getWidth();
        }

        for (TextColor color : TextColor.values()) {
            colorMap.put(color, new Color(color.r, color.g, color.b));
        }
    }

    public void draw(Queue<DrawCommand> drawCommands) {
        clear();
        while (!drawCommands.isEmpty()) {
            drawCommands.poll().accept(this);
        }
    }

    private void clear() {
        g.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
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
        return (int) (normalized * buffer.getWidth());
    }

    @Override
    public void visit(DrawImageCommand cmd) {
        int x = convertToBufferPixels(cmd.x());
        int y = convertToBufferPixels(cmd.y());
        int w = convertToBufferPixels(cmd.w());
        int h = convertToBufferPixels(cmd.h());
        BufferedImage img = textureMap.get(cmd.texture());

        g.drawImage(img, x, y, w, h,null);
    }

    @Override
    public void visit(DrawTextCommand cmd) {
        int x = convertToBufferPixels(cmd.x());
        int y = convertToBufferPixels(cmd.y());
        Font font = fontMap.get(cmd.font());
        g.setColor(colorMap.get(cmd.color()));
        g.setFont(font);
        g.drawString(cmd.text(), x, y);
    }

    public BufferedImage getBuffer() {
        return buffer;
    }
}
