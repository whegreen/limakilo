package id.limakilo.www.bawang.util.social;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.supportkit.core.User;
import io.supportkit.ui.ConversationActivity;
import io.supportkit.ui.fragment.ConversationFragment;

/**
 * Created by walesadanto on 13/9/15.
 */
public class SupportkitKit {

    private User skUser;
    private final Map<String, Object> customProperties = new HashMap<>();


    public SupportkitKit(){
        skUser = User.getCurrentUser();
    }

    public void setupUser(){
        skUser = User.getCurrentUser();

        skUser.setFirstName("Artour");
        skUser.setLastName("Babaev");
        skUser.setEmail("2ez@4rtz.com");
        skUser.setSignedUpAt(new Date(1420070400000l));

        addCustomProperties("timeStamp", System.currentTimeMillis());
        skUser.addProperties(customProperties);
    }

    public void addCustomProperties(String key, String value){
        customProperties.put(key, value);
    }

    public void addCustomProperties(String key, Boolean value){
        customProperties.put(key, value);
    }

    public void addCustomProperties(String key, int value){
        customProperties.put(key, value);
    }

    public void addCustomProperties(String key, Long value){
        customProperties.put(key, value);
    }
}
