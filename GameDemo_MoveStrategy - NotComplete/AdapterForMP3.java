public class AdapterForMP3 implements IMyMediaPlayer{
    private MP3 mp3 = new MP3();
    
    public void open(String fname){
        mp3.openMP3(fname);
    }
    public void play(){
        mp3.playMP3();
    }
    public void stop(){
        mp3.stopMP3();
    }    
    public void close(){
        System.out.println("MP3 don't have clost function");
    }
}