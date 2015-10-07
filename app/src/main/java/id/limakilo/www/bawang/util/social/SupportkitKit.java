package id.limakilo.www.bawang.util.social;

import android.content.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import id.limakilo.www.bawang.util.common.PreferencesManager;
import io.supportkit.core.User;

/**
 * Created by walesadanto on 13/9/15.
 */
public class SupportkitKit {

    private User skUser;
    private final Map<String, Object> customProperties = new HashMap<>();


    public SupportkitKit(){
        skUser = User.getCurrentUser();
    }


    public void setupUser(Context context){
        skUser = User.getCurrentUser();

        String firstName = PreferencesManager.getAsString(context, PreferencesManager.FIRST_NAME);
        String lastName = PreferencesManager.getAsString(context, PreferencesManager.LAST_NAME);
        String handphone = PreferencesManager.getAsString(context, PreferencesManager.HANDPHONE);

        if (firstName != null){
            skUser.setFirstName(firstName);
        }

        if (firstName != null){
            skUser.setFirstName(lastName);
        }

        if (handphone != null){
            skUser.setFirstName(lastName);
        }

        skUser.setEmail(handphone);
        skUser.setSignedUpAt(new Date(System.currentTimeMillis()));

//        addCustomProperties("timeStamp", System.currentTimeMillis());
//        skUser.addProperties(customProperties);
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
