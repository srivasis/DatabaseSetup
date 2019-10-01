var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://10.128.155.23:27017/";

MongoClient.connect(url, function(err, db){
    if(err) throw err;
    var db_OneJira = db.db("onejira");
    db_OneJira.listCollections().toArray(function(err, collections){
        if(err) throw err;
        var j = 0;
        var k = 0;
        for(var i = 0; i < collections.length; i++){
            if(collections[i].name.includes("Userstory")){
                k++;
                var colName = collections[i].name;
                db_OneJira.collection(collections[i].name, function(err, collection){
                    if(err){
                        throw err;
                    }else{
                        var colName2 = colName.split("Userstory")[0];
                        collection.aggregate([{
                            $unwind: "$issues"
                        },
                        {
                            $project: {
                                _id: {$arrayElemAt : [{$split: ["$issues.key", "-"]}, 0]},
                                points: "$issues.fields.customfield_10006"
                            }
                        },{
                            $match: {
                                points: {$ne: null}
                            }
                        }]).toArray(function(err, result){
                            if(err){
                                console.log(err);
                            }else{
                                var db_Query = db.db("queries");
                                var res = "SCRUM";
                                if(result.length === 0){
                                    res = "KANBAN";
                                }
                                var toInsert = {_id: colName2, type: res};
                                db_Query.collection("TeamType").insert(toInsert, function(err, res){
                                    if(err){
                                        console.log("Cannot insert");
                                        j++;
                                        throw err;
                                    }
                                    console.log("Inserted");
                                    j++;

                                    if(j == k){
                                        db.close();
                                    }
                                });
                            }
                        })
                    }
                });
            }
        }
    });
});
