package audio.commands;

public interface AudioCommand {
    public void accept(AudioCommandVisitor visitor);
}
