var exec = require('cordova/exec');

exports.open = function (success, error) {
    exec(success, error, 'FileExplorer', 'open', []);
};
