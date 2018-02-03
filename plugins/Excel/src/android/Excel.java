package easyexcel.plugins;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class Excel extends CordovaPlugin {
    private CallbackContext callbackContext;
    private static final int FILE_SELECT_CODE = 0;

    private void open(CallbackContext callbackContext) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        Intent chooser = Intent.createChooser(intent, "Select File");
        cordova.startActivityForResult(this, chooser, FILE_SELECT_CODE);

        PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true);
        this.callbackContext = callbackContext;
//        callbackContext.sendPluginResult(pluginResult);
    }
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            this.open( callbackContext);
            Log.e("tag", "sdkjfskdjhf");
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILE_SELECT_CODE && callbackContext != null) {

            if (resultCode == Activity.RESULT_OK) {

                Uri uri = data.getData();

                if (uri != null) {
                    callbackContext.success(uri.toString());
                } else {
                    callbackContext.error("File uri was null");
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // TODO NO_RESULT or error callback?
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                callbackContext.sendPluginResult(pluginResult);
            } else {
                callbackContext.error(resultCode);
            }
        }
    }
}
