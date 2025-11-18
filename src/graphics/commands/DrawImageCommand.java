package graphics.commands;

import graphics.Texture;

public record DrawImageCommand(float x, float y, float w, float h, Texture texture) implements DrawCommand {

    @Override
    public void accept(DrawCommandVisitor visitor) {
        visitor.visit(this);
    }
}
