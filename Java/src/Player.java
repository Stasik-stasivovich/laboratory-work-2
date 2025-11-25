import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;


public class Player {
    private int currentSoundIndex = 1;
    private int numberOfSounds = 1;
    private Clip clip = null;
    private boolean isPlaying = false;

    Player(){

    }
    // поставити на паузу/ зняти паузу
    public void toggleMusic(){
        if (isPlaying){
            stop();
        }
        else{
            play();
        }

    }
    // запустити
    public void play(){
        if (clip != null && !clip.isRunning()) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isPlaying = true;
        }
        else{
            loadAndPlay();
        }
    }
    // якщо трек не загружений то загружає
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
    // переключаємо трек на наступний
    public void nextTrack(){
        stop();
        if (clip != null) clip.close();
        currentSoundIndex++;
        if (currentSoundIndex > numberOfSounds)
            currentSoundIndex = 1;
        clip = null;
        play();
    }
    // переключаємо трек на попередній
    public void prevTrack(){
        stop();
        if (clip != null) clip.close();
        currentSoundIndex--;
        if (currentSoundIndex < 1)
            currentSoundIndex = numberOfSounds;
        clip = null;
        play();
    }
    // зупиняємо музику
    public void stop(){
        if (clip != null && clip.isRunning()) clip.stop();
        isPlaying = false;
    }
    // отримати шлях до музики за індексом
    private  String getPath(int index){
        return "music/track"+index+".wav";
    }
}
