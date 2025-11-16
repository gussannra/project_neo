package graphics.commands;

import graphics.TextColor;
import graphics.TextFont;

public record DrawTextCommand(String text, float x, float y, TextFont font, TextColor color) implements DrawCommand {
    @Override
    public void accept(DrawCommandVisitor visitor) {
        visitor.visit(this);
    }
}
