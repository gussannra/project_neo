package audio.commands;

public interface AudioCommandVisitor {
    public void visit(PlayAudioCommand cmd);
}
