var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://10.128.155.23:27017/";

MongoClient.connect(url, function(err, db){
    if(err) throw err;
    var db_Queries = db.db("queries");
    db_Queries.dropDatabase().then(function(res, err){
        if(err){
            console.log("Cannot Delete Database");
            throw err;
        }
        console.log("Successfully Deleted queries DB");
        db.close();
    });
});
