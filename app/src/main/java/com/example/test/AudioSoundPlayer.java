package com.example.test;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;

import java.io.InputStream;

public class AudioSoundPlayer {

    private SparseArray<PlayThread> threadMap = null;
    private Context context;
    private static final SparseArray<String> SOUND_MAP = new SparseArray<>();
    public static final int MAX_VOLUME = 100, CURRENT_VOLUME = 90;


    static {
        // white keys sounds
        SOUND_MAP.put(1, "c3");
        SOUND_MAP.put(2, "d3");
        SOUND_MAP.put(3, "e3");
        SOUND_MAP.put(4, "f3");
        SOUND_MAP.put(5, "g3");
        SOUND_MAP.put(6, "a3");
        SOUND_MAP.put(7, "b3");
        SOUND_MAP.put(8, "c4");
        SOUND_MAP.put(9, "d4");
        SOUND_MAP.put(10, "e4");
        SOUND_MAP.put(11, "f4");
        SOUND_MAP.put(12, "g4");
        SOUND_MAP.put(13, "a4");
        SOUND_MAP.put(14, "b4");
        // black keys sounds
        SOUND_MAP.put(15, "db3");
        SOUND_MAP.put(16, "eb3");
        SOUND_MAP.put(17, "gb3");
        SOUND_MAP.put(18, "ab3");
        SOUND_MAP.put(19, "bb3");
        SOUND_MAP.put(20, "db4");
        SOUND_MAP.put(21, "eb4");
        SOUND_MAP.put(22, "gb4");
        SOUND_MAP.put(23, "ab4");
        SOUND_MAP.put(24, "gb4");
    }

    public AudioSoundPlayer(Context context) {
        this.context = context;
        threadMap = new SparseArray<>();
    }

    public void playNote(int note) {
        if (!isNotePlaying(note)) {
            Log.d("PlayNote", String.valueOf(note));


            PlayThread thread = new PlayThread(note);
            thread.start();
            threadMap.put(note, thread);
        }
    }

    public void stopNote(int note) {
        PlayThread thread = threadMap.get(note);

        if (thread != null) {
            threadMap.remove(note);
        }
    }

    public boolean isNotePlaying(int note) {
        return threadMap.get(note) != null;
    }

    private class PlayThread extends Thread {
        int note;
        AudioTrack audioTrack;
        MediaPlayer player;

        public PlayThread(int note) {
            this.note = note;
        }

        @Override
        public void run() {
            try {
                int id;
                String path = SOUND_MAP.get(note) + ".m4a";
                Log.d("NotePath", path);
                Log.d("NotePath", String.valueOf(note));
                switch (note){
                    case 1:
                        id = R.raw.c3;
                        break;
                    case 2:
                        id = R.raw.d3;
                        break;
                    case 3:
                        id = R.raw.e3;
                        break;
                    case 4:
                        id = R.raw.f3;
                        break;
                    case 5:
                        id = R.raw.g3;
                        break;
                    case 6:
                        id = R.raw.a3;
                        break;
                    case 7:
                        id = R.raw.b3;
                        break;
                    case 8:
                        id = R.raw.c4;
                        break;
                    case 9:
                        id = R.raw.d4;
                        break;
                    case 10:
                        id = R.raw.e4;
                        break;
                    case 11:
                        id = R.raw.f4;
                        break;
                    case 12:
                        id = R.raw.g4;
                        break;
                    case 13:
                        id = R.raw.a4;
                        break;
                    case 14:
                        id = R.raw.b4;
                        break;
                    case 15:
                        id = R.raw.db3;
                        break;
                    case 16:
                        id = R.raw.eb3;
                        break;
                    case 17:
                        id = R.raw.gb3;
                        break;
                    case 18:
                        id = R.raw.ab3;
                        break;
                    case 19:
                        id = R.raw.bb3;
                        break;
                    case 20:
                        id = R.raw.db4;
                        break;
                    case 21:
                        id = R.raw.eb4;
                        break;
                    case 22:
                        id = R.raw.gb4;
                        break;
                    case 23:
                        id = R.raw.ab4;
                        break;
                    case 24:
                        id = R.raw.bb4;
                        break;
                    default:
                        id = R.raw.c3;
                        break;

                }

                player = new MediaPlayer();
                player = MediaPlayer.create(context, id);
                player.start();

                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        player.release();
                    }
                });


                /*String path = SOUND_MAP.get(note) + ".m4a";
                Log.d("NotePath", path);
                AssetManager assetManager = context.getAssets();
                AssetFileDescriptor ad = assetManager.openFd(path);
                long fileSize = ad.getLength();
                int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];

                audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                        AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STREAM);

                float logVolume = (float) (1 - (Math.log(MAX_VOLUME - CURRENT_VOLUME) / Math.log(MAX_VOLUME)));
                audioTrack.setStereoVolume(logVolume, logVolume);

                audioTrack.play();
                InputStream audioStream = null;
                int headerOffset = 0x2C; long bytesWritten = 0; int bytesRead = 0;

                audioStream = assetManager.open(path);
                audioStream.read(buffer, 0, headerOffset);

                while (bytesWritten < fileSize - headerOffset) {
                    bytesRead = audioStream.read(buffer, 0, bufferSize);
                    bytesWritten += audioTrack.write(buffer, 0, bytesRead);
                }

                audioTrack.stop();
                audioTrack.release();

                 */

            } catch (Exception e) {
            } finally {
                //if (audioTrack != null) {
                   // audioTrack.release();

            }
        }
    }

}