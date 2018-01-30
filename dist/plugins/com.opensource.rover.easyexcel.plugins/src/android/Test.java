package com.opensource.rover.easyexcel.plugins;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
/**
 * This class echoes a string called from JavaScript.
 */
public class Test extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            Log.i("plugin","hello plugin success");
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
