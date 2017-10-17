
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import net.respina.teslaa.ord.SignUpActivity;
import net.respina.teslaa.ord.MainMenu;

import java.util.HashMap;


/**
 * This class manages persisted records of users data
 */
public class SessionManager {

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private Editor editor;

    // Context
    private Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Shared pref file name
    private static final String PREF_NAME =  "**********";

    // All shared Prefrences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (for making variable package level accessibility)
    public static final String KEY_NAME = "_NAME";

    // User Family Name (for making variable package level accessibility)
    public static final String KEY_FAMILY = "_FAMILY";

    // Email Address (for making variable package level accessibility)
    public static final String KEY_EMAIL = "_EMAILADDRESS";

    // User UUID (for making variable package level accessibility)
    public static final String KEY_UUID = "_USERUUID";

    // User PhoneNumber (for making variable package level accessibility)
    public static final String KEY_PHONE = "_USERPHONE";

    // User SESSIONID (for making variable package level accessibility)
    public static final String KEY_SESSIONID = "_SESSIONID";

    // User BALANCE (for making variable package level accessibility)
    public static final String KEY_BALANCE = "_BALANCE";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     *
     * @param name
     * @param uuid
     * @param phone
     */
    public void createLoginSession(String name, String family, String uuid,String phone){
        // Storing value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing uuid in pref
        editor.putString(KEY_UUID, uuid);

        // Storing family name in pref
        editor.putString(KEY_FAMILY, family);

        // Storing phone in pref
        editor.putString(KEY_PHONE, phone);

        // commit changes
        editor.commit();
    }

    public void insertSessionId(String sessionID){
        editor.putString(KEY_SESSIONID, sessionID);
        editor.commit();
    }

    public void insertPhoneNumber(String phone){
        // Storing phone in pref
        editor.putString(KEY_PHONE, phone);
        editor.commit();
    }

    public void insertFamilyName(String name, String family){
        editor.putString(KEY_FAMILY, family);
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public void insertBalance(String balance){
        // Storing balance
        editor.putString(KEY_BALANCE, balance);
        editor.commit();
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails(){
        // getting all user data
        HashMap<String, String> user = new HashMap<>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user family address
        user.put(KEY_FAMILY, pref.getString(KEY_FAMILY, null));

        // user email address
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // user UUID
        user.put(KEY_UUID, pref.getString(KEY_UUID, null));

        // user Phone Number
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));

        // user SESSIONID
        user.put(KEY_SESSIONID, pref.getString(KEY_SESSIONID, null));

        // user BALANCE
        user.put(KEY_BALANCE, pref.getString(KEY_BALANCE, null));

        // return user
        return user;
    }

    /**
     * Check login method will check user login status
     * if false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin(){

        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login activity
            Intent i = new Intent(_context, SignUpActivity.class);
            // Closing all the activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        } else {
            Intent i = new Intent(_context, MainMenu.class);
            _context.startActivity(i);
        }
    }

    /**
     * Creating logout process controller
     */
    public void logoutUser(){

        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, SignUpActivity.class);

        // Closing all the activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Starting Login Activity
        _context.startActivity(i);
    }

    // Get login state
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, true);
    }


}

