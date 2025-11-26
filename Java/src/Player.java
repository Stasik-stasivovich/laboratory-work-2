import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;

/**
 * class with MusicPlayer logic
 */
public class Player {
    private int currentSoundIndex = 1;
    private int numberOfSounds = 8;
    private Clip clip = null;
    private boolean isPlaying = false;

    Player(){

    }
    // поставити на паузу/ зняти паузу

    /**
     * turn on pause / turn off pause
     */
    public void toggleMusic(){
        if (isPlaying){
            stop();
        }
        else{
            play();
        }

    }

    /**
     * start
     */
    public void play(){
        if (clip != null && !clip.isRunning()) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isPlaying = true;
        }
        else{
            loadAndPlay();
        }
    }
    // якщо трека немає в змінній то завантажуємо трек за індексом
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
    /**
    go to next track
     */
    public void nextTrack(){
        stop();
        if (clip != null) clip.close();
        currentSoundIndex++;
        if (currentSoundIndex > numberOfSounds)
            currentSoundIndex = 1;
        clip = null;
        play();
    }

    /**
     * go to previous track
     */

    public void prevTrack(){
        stop();
        if (clip != null) clip.close();
        currentSoundIndex--;
        if (currentSoundIndex < 1)
            currentSoundIndex = numberOfSounds;
        clip = null;
        play();
    }

    /**
     * stop
     */
    public void stop(){
        if (clip != null && clip.isRunning()) clip.stop();
        isPlaying = false;
    }
    // отримати шлях до музики за індексом
    private  String getPath(int index){
        return "music/track"+index+".wav";
    }
}
