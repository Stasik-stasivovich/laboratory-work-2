import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;


public class Player {
    private int currentSoundIndex = 1;
    private int numberOfSounds = 3;
    private Clip clip = null;
    private boolean isPlaying = false;

    Player(){

    }
    public void toggleMusic(){
        if (isPlaying){
            stop();
        }
        else{
            play();
        }

    }
    public void play(){
        if (clip != null && !clip.isRunning()) {
            clip.start();
            isPlaying = true;
        }
        else{
            loadAndPlay();
        }
    }

    private void loadAndPlay() {
        try{
            if (clip != null ) clip.close();
            String path = getPath(currentSoundIndex);
            File musicFile = new File(path);
            if (!musicFile.exists())return;
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(musicFile));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isPlaying = true;

        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public void nextTrack(){
        stop();
        if (clip != null) clip.close();
        currentSoundIndex++;
        if (currentSoundIndex > numberOfSounds)
            currentSoundIndex = 1;
        clip = null;
        play();
    }
    public void prevTrack(){
        stop();
        if (clip != null) clip.close();
        currentSoundIndex--;
        if (currentSoundIndex < 1)
            currentSoundIndex = numberOfSounds;
        clip = null;
        play();
    }
    public void stop(){
        if (clip != null && clip.isRunning()) clip.stop();
        isPlaying = false;
    }
    private  String getPath(int index){
        return "music/track"+index+".wav";
    }
}
