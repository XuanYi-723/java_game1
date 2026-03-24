public class MusicTest {
    public void playMusic(String fn, IMyMediaPlayer player) {

         player.open(fn);

         player.play();    
         try{
            Thread.sleep(5000);
         }catch(InterruptedException e){
             e.printStackTrace();
         }
         player.stop(); 

         player.close();

      }
    public static void main(String[] args) {

        MusicTest tt = new MusicTest();

        //play MP3 music

        tt.playMusic("mp3sound.mp3",  new AdapterForMP3()) ;

        //play Midi music
        tt.playMusic("midi.mid",  new AdapterForMidi());

       //play WAV music
        tt.playMusic("wavsound.wav",  new AdapterForWAV());

    }
}