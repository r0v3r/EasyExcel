package plugin.file;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class FileExplorer extends CordovaPlugin {
    private static final int PICK_FILE_REQUEST = 1;
    private CallbackContext callback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("open")) {
            this.open(callbackContext);
            return true;
        }
        return false;
    }

    private void open(CallbackContext callbackContext) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        Intent chooser = Intent.createChooser(intent, "Select File");
        cordova.startActivityForResult(this, chooser, PICK_FILE_REQUEST);

        PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true);
        callback = callbackContext;
        callbackContext.sendPluginResult(pluginResult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FILE_REQUEST && callback != null) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    Log.w("file explorer", "file uri: " + uri.toString());
                    callback.success(uri.toString());
                } else {
                    callback.error("File uri was null");
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // TODO NO_RESULT or error callback?
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                callback.sendPluginResult(pluginResult);
            } else {
                callback.error(resultCode);
            }
        }
    }
}
