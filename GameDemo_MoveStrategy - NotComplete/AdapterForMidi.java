public class AdapterForMidi implements IMyMediaPlayer{
    private Midi midi = new Midi();
    
    public void open(String fname){
        midi.openMidi(fname);
    }
    public void play(){
        midi.playMidi();
    }
    public void stop(){
        midi.stopMidi();
    }    
    public void close(){
        midi.closeMidi();
    }
}