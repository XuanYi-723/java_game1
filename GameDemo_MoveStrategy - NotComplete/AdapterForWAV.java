public class AdapterForWAV implements IMyMediaPlayer{
    private WAV wav = new WAV();
    
    public void open(String fname){
        wav.openWAV(fname);
    }
    public void play(){
        wav.playWAV();
    }
    public void stop(){
        wav.stopWAV();
    }    
    public void close(){
        wav.closeWAV();
    }
}