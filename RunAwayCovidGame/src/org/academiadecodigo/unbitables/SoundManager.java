package org.academiadecodigo.unbitables;

import java.util.HashMap;
import sound.Sound;

public class SoundManager {

    private static HashMap<String, Sound> soundHashMap;

    public SoundManager() {

        soundHashMap = new HashMap<>();
        soundHashMap.put("background", new Sound("background2.wav"));
        soundHashMap.put("blip nasty", new Sound("blip nasty.wav"));
        soundHashMap.put("maskAppear", new Sound("appear mask.wav"));
        soundHashMap.put("vaccine", new Sound("vaccineappear1.wav"));
        soundHashMap.put("bling", new Sound("bling.wav"));
        soundHashMap.put("vaccineEffect", new Sound("Vaccine Effect Final.wav"));
        soundHashMap.put("menu", new Sound("Menu.wav"));
        soundHashMap.put("collision", new Sound("collision.wav"));
       /* soundHashMap.put("background", new Sound("backgrounds.wav"));
        soundHashMap.put("background", new Sound("backgrounds.wav"));
        soundHashMap.put("background", new Sound("backgrounds.wav"));
        soundHashMap.put("background", new Sound("backgrounds.wav"));
        soundHashMap.put("background", new Sound("backgrounds.wav"));
        soundHashMap.put("background", new Sound("backgrounds.wav"));
*/

    }





    public static void playSound(String sound) {
        soundHashMap.get(sound).play(true);
    }


    public static Sound getSound(String sound){
        return soundHashMap.get(sound);
    }


    public void stopHandler(){
        for (String sound : soundHashMap.keySet()){
            soundHashMap.get(sound).stop();
        }
    }

}
