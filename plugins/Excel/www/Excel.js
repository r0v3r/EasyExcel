var exec = require('cordova/exec');

exports.open = function (success, error) {
    exec(success, error, 'Excel', 'open', []);
};

exports.read = function(path, success, error){
    exec(success, error, 'Excel', 'read', [path]);
}
