package audio;

import audio.commands.AudioCommand;
import audio.commands.AudioCommandVisitor;
import audio.commands.PlayAudioCommand;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer implements AudioCommandVisitor {
    SourceDataLine line;
    AudioFormat format;
    DataLine.Info info;
    byte[] audioBuffer;
    int sampleRate = 44_100;
    int samplesPerFrame = sampleRate / 60;
    int bytesPerSample = 2;
    int channels = 2;
    float phase = 0.0f;

    Map<Sound, AudioInputStream> soundMap = new HashMap<>();
    List<Sound> currentSounds = new ArrayList<>();

    public AudioPlayer() {
        format = new AudioFormat(sampleRate, bytesPerSample * 8, channels, true, false);
        info = new DataLine.Info(SourceDataLine.class, format);
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, sampleRate * bytesPerSample * channels);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        audioBuffer = new byte[samplesPerFrame * bytesPerSample * channels];
    }

    public void writeAudio(Queue<AudioCommand> audioCommands) {
        while (!audioCommands.isEmpty()) {
            audioCommands.poll().accept(this);
        }

        writeAudioStream();
    }

//    public void writeAudio() {
//        final float twoPi = (float) (2 * Math.PI);
//        float hertz = 440.0f;
//        float increment = hertz / sampleRate * twoPi;
//
//        for (int sample = 0; sample < samplesPerFrame; sample++) {
//            float value = (float) Math.sin(phase);
//            value *= 0.4;
//            phase += increment;
//
//            if (phase > twoPi) {
//                phase -= twoPi;
//            }
//
//            short outValue = (short) (value * Short.MAX_VALUE);
//            byte lsb = (byte) (outValue >> 8);
//            byte msb = (byte) (outValue & 0x00FF);
//
//            audioBuffer[(sample * 4) + 0] = msb;
//            audioBuffer[(sample * 4) + 1] = lsb;
//            audioBuffer[(sample * 4) + 2] = msb;
//            audioBuffer[(sample * 4) + 3] = lsb;
//        }
//
//        line.write(audioBuffer, 0, samplesPerFrame * bytesPerSample * channels);
//    }

    public void start() {
        line.start();
    }

    public void loadFile(Sound sound, File file) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            audioStream.mark(Integer.MAX_VALUE);
            soundMap.put(sound, audioStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAudioStream() {
        int bytesToWrite = samplesPerFrame * bytesPerSample * channels;
        Arrays.fill(audioBuffer, (byte) 0);
        for (Sound sound : currentSounds) {
            AudioInputStream audioStream = soundMap.get(sound);
            try {
                byte[] buffer = new byte[bytesToWrite];
                int bytesRead = audioStream.read(buffer, 0, bytesToWrite);
                if (bytesRead == -1) {
                    audioStream.reset();
                    currentSounds.remove(sound);
                }

                for (int i = 0; i < bytesToWrite; ++i) {
                    audioBuffer[i] += buffer[i];
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        line.write(audioBuffer, 0, bytesToWrite);
    }

    @Override
    public void visit(PlayAudioCommand cmd) {
        currentSounds.add(cmd.sound());
    }
}
