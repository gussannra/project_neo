package graphics.commands;

import graphics.Texture;

public record DrawImageCommand(float x, float y, Texture texture) implements DrawCommand {

    @Override
    public void accept(DrawCommandVisitor visitor) {
        visitor.visit(this);
    }
}
