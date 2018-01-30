package plugin.logcat;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class Logcat extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        String tag = args.getString(0);
        String message = args.getString(1);
        if (action.equals("i")) {
            this.i(tag, message);
            return true;
        } else if (action.equals("w")) {
            this.w(tag, message);
            return true;
        } else if (action.equals("e")) {
            this.e(tag, message);
            return true;
        } else if (action.equals("d")) {
            this.d(tag, message);
            return true;
        } else {
            this.v(tag, message);
            return true;
        }
    }

    private void v(String tag, String message) {
        Log.v(tag, message);
    }

    private void d(String tag, String message) {
        Log.d(tag, message);
    }

    private void i(String tag, String message) {
        Log.i(tag, message);
    }

    private void w(String tag, String message) {
        Log.w(tag, message);
    }

    private void e(String tag, String message) {
        Log.e(tag, message);
    }
}
