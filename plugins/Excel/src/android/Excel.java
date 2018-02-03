package easyexcel.plugins;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * This class echoes a string called from JavaScript.
 */
public class Excel extends CordovaPlugin {
    private CallbackContext callbackContext;
    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG = Excel.class.getName();

    private static final String WRITE_PERMISSION= "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final int WRITE_PERMISSION_REQUEST_CODE=0;

    private Uri fileUri;

    private void open(CallbackContext callbackContext) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        Intent chooser = Intent.createChooser(intent, "Select File");
        cordova.startActivityForResult(this, chooser, FILE_SELECT_CODE);

        PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true);
        this.callbackContext = callbackContext;
    }

    private void read(Uri uri, CallbackContext callbackContext){
        String filePath = uri.getPath();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = workbook.getSheet(0);
            int sheetRows = sheet.getRows();
            int sheetColumns = sheet.getColumns();  
            Log.w(TAG, sheet.getName());
            callbackContext.success("success");
        } catch (IOException e) {
            e.printStackTrace();
            callbackContext.error("打开文件错误");
        } catch (BiffException e) {
            e.printStackTrace();
            callbackContext.error("暂不支持此文件");
        }
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("open")) {
            this.open(callbackContext);
            return true;
        }else if(action.equals("read")){
            fileUri = new Uri.Builder().path(args.getString(0)).build();
            if(!cordova.hasPermission(WRITE_PERMISSION)){
                cordova.requestPermission(this, WRITE_PERMISSION_REQUEST_CODE, WRITE_PERMISSION);
            }else{
                this.read(fileUri, callbackContext);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILE_SELECT_CODE && callbackContext != null) {
            if (resultCode == Activity.RESULT_OK) {
                fileUri = data.getData();
                if (fileUri != null) {
                    Log.i(TAG, "request to open file: " +fileUri.getPath());
                    callbackContext.success(fileUri.getPath());
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

    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) throws JSONException{
        for(int r:grantResults){
            if(r == PackageManager.PERMISSION_DENIED){
                this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "用户不同意该权限,程序无法正常执行"));
                return;
            }
        }
        switch(requestCode){
            case WRITE_PERMISSION_REQUEST_CODE:
                read(fileUri,callbackContext);
                break;
        }
    }
}
