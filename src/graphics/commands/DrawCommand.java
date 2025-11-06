package graphics.commands;

public interface DrawCommand {
    public void accept(DrawCommandVisitor visitor);
}
