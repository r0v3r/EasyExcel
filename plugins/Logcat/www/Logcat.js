var exec = require("cordova/exec");

exports.v = function(tag, message) {
  exec(success, error, "Logcat", "v", [tag, message]);
};
exports.d = function(tag, message) {
  exec(success, error, "Logcat", "d", [tag, message]);
};
exports.i = function(tag, message) {
  exec(success, error, "Logcat", "i", [tag, message]);
};
exports.w = function(tag, message) {
  exec(success, error, "Logcat", "w", [tag, message]);
};
exports.e = function(tag, message) {
  exec(success, error, "Logcat", "e", [tag, message]);
};

let success = function(msg){

}

let error = function(msg){
    
}
