package com.example.javafx;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.beans.PropertyVetoException;
import java.util.Locale;

public class Sound {
    public static void init(String voiceName) throws PropertyVetoException {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        try {
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            Synthesizer syn = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            syn.allocate();
            syn.speakPlainText(voiceName, null);
        } catch (EngineException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            init("hello");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

}
