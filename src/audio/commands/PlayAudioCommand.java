package audio.commands;

import audio.Sound;

public record PlayAudioCommand(Sound sound) implements AudioCommand {
    @Override
    public void accept(AudioCommandVisitor visitor) {
        visitor.visit(this);
    }
}
