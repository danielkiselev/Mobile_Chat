package project03.csc214.project03.Sound;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.os.Build;

import project03.csc214.project03.R;

/**
 * Created by Daniel on 6/27/2017.
 */

public class SoundManager {

    public static SoundManager getInstance;
    protected SoundPool soundPool;
    public static int sound_add;
    public static int sound_send;
    private Context context;

    public SoundManager(Context context){
        soundPool = new SoundPool.Builder().setMaxStreams(20).build();
        sound_add = soundPool.load(context, R.raw.another,1);
        sound_send = soundPool.load(context, R.raw.boop,1);
        getInstance = this;
    }

    public void playSound(int sound){
        soundPool.play(sound, 1.0f,1.0f,1,0,1.0f);
    }
}
