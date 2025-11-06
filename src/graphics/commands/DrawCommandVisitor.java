package graphics.commands;

public interface DrawCommandVisitor {
    public void visit(DrawImageCommand cmd);
}
