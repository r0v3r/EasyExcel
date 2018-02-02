## Before Development
- This project is a hybrid app. `app` folder contains the source code of this project, which is a `Vue.js` based application. `dist` folder is to hold the compiled code and package for different platforms, now using `cordova`. `plugins` folder holds the plugins to interact with native apis, for `cordova` is used for multi-platform support so plugins are now all cordova plugins.

- Developers could use `yarn run dev` to open a chrome tab to see the out-line of this app. However because this vue project in `app` folder doesn't contain cordova dependency, so they could not use all the functions unless install it on a phone.

- To build this project, please 
    1. build the source code by running `yarn run build` in `./app` folder
    2. add platform for this project, currently only support android, `cordova platform add --platform_name android` and then `cordova run android` in `./dist` folder

- when web page is longer than phone screen, cordova would display bounce effect, add these two preference would solve this problem
  ```
  <preference name="WebViewBounce" value="false" />
  <preference name="DisallowOverscroll" value="true" />
  ```

- when using `plugman` as plugin build cli, please use 
    ```
    plugman create --name <pluginName> --plugin_id <pluginID> --plugin_version <version>
    ```
    to create. Then `plugman platform add --platform_name <platform>` to support a platform. Because the plugman bug, developer should edit the `<source-file>` node in plugin.xml, change `target-dir` according to your `plugin_id` when you create the plugin.[eg. if `plugin_id` is aaa-bbb, then change the `target-dir` to `src/aaa/bbb/` ]

- after created the plugin, use 
    ```
    sudo plugman createpackagejson ./
    ```
    in the `plugin dir`