const newman = require('newman');

newman.run({
collection: require('./bonus_postman_collection.json'),
debug: true,
reporters: ['htmlextra','cli'],
reporter: {
    'htmlextra': {
        export: './report.html',
    },
},
}, function (err) {
    if (err) { throw err; }
    console.log('collection run complete!');
});