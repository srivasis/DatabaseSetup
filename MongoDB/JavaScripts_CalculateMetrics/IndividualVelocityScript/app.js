var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://10.128.155.23:27017/";

MongoClient.connect(url, function(err, db){
    if(err) throw err;
    var db_OneJira = db.db("onejira");
    var curr = 0;
    var max = 0;
    db_OneJira.listCollections().toArray(function(err, collections){
        if(err) throw err;
        for(var i = 0; i < collections.length; i++){
            if(collections[i].name.includes("Userstory")){
            var colName = collections[i].name;
            db_OneJira.collection(collections[i].name, function(err, collection){
                if(err){
                    throw err;
                }else{
                    var colName2 = colName;
                    collection.aggregate([{
                        $unwind: "$issues"
                        },{
                            $unwind: "$issues.fields.customfield_10004"
                        }, {
                            $project: {
                                member: "$issues.fields.assignee.displayName",
                                story_points: "$issues.fields.customfield_10006",
                                status: "$issues.fields.status.statusCategory.name",
                                issue_id: "$issues.id",
                                start_date: {$arrayElemAt: [{$split: [{$arrayElemAt: [{$split: ["$issues.fields.customfield_10004", ","]}, 4]}, "="]}, 1]},
                                sprint_name: {$arrayElemAt: [{$split: [{$arrayElemAt: [{$split: ["$issues.fields.customfield_10004", ","]}, 3]}, "="]}, 1]}
                            }
                        },{
                            $match: {
                                story_points: { $ne: null},
                                member: { $ne: null, $exists: true }, 
                                start_date: {$ne: null, $ne: "<null>"},
                            }
                        }, {
                            $group: {
                                _id:{ 
                                    status: "$status",
                                    issue_id: "$issue_id"
                                },
                                Data: {
                                    $push: {
                                        story_points: "$story_points",
                                        member: "$member", 
                                        start_date: "$start_date",
                                        sprint_name: "$sprint_name"
                                    }
                                }
                            }
                        }, {
                            $project: {
                                _id: {
                                    status: "$_id.status",
                                    issue_id: "$_id.issue_id"
                                },
                                Data: {
                                    $arrayElemAt: ["$Data",0]
                                }
                            }
                        }, {
                            $group: {
                                _id: {
                                    start_date: "$Data.start_date"
                                },
                                Data: {
                                    $push: {
                                        issue_id: "$_id.issue_id", 
                                        status: "$_id.status", 
                                        story_points: "$Data.story_points", 
                                        member: "$Data.member",
                                        sprint_name: "$Data.sprint_name"
                                    }
                                }
                            }
                        }, {
                            $sort: {_id: -1}
                        }, {
                            $skip: 1
                        }]).toArray(function(err, result){
                            var teamName = colName2.substring(0, 10);
                            var foundIssues = new Map();
                            var members = new Map();
                            var sprintSet = [];
                            for (var i = 0; i < result.length; i++){
                                sprintSet.push(result[i].Data[0].sprint_name + '||' + result[i]._id.start_date);
                            }
                            for(var i = 0; i < result.length; i++){
                                for(var j = 0; j < result[i].Data.length; j++){
                                    if(foundIssues.has(result[i].Data[j].issue_id)){
                                        if(foundIssues.get(result[i].Data[j].issue_id) === "Done"){
                                            continue;
                                        }
                                        else if(result[i].Data[j].status === "Done" && 
                                        foundIssues.get(result[i].Data[j].issue_id) != "Done"){
                                            if(!members.has(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date)){
                                                for (var k = 0; k < sprintSet.length; k++){
                                                    members.set(result[i].Data[j].member+'||'+teamName+'||'+sprintSet[k], [0,0]);
                                                } 
                                            }
                                            var oldValue = members.get(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date);
                                            
                                            if(oldValue === undefined){
                                                members.set(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date, [0, 0]);
                                            }
                                            oldValue = members.get(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date);
                                            members.set(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date, [oldValue[0], oldValue[1] + 
                                                result[i].Data[j].story_points]);
                                            foundIssues.set(result[i].Data[j].issue_id, "Done");
                                        }
                                    }else{
                                        foundIssues.set(result[i].Data[j].issue_id, result[i].Data[j].status);
                                        if(members.has(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date)){
                                            var oldValue = members.get(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date);
                                            if(result[i].Data[j].status === "Done"){
                                                members.set(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date, [oldValue[0] + result[i].Data[j].story_points, 
                                                    oldValue[1] + result[i].Data[j].story_points]);
                                            }else {
                                                    members.set(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date, [oldValue[0] + result[i].Data[j].story_points, 
                                                        oldValue[1]]);
                                            }
                                        }else{
                                            for (var k = 0; k < sprintSet.length; k++){
                                                members.set(result[i].Data[j].member+'||'+teamName+'||'+sprintSet[k], [0,0]);
                                            } 
                                            if(result[i].Data[j].status === "Done"){
                                                members.set(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date, [result[i].Data[j].story_points, 
                                                    result[i].Data[j].story_points]);
                                            }else {
                                                members.set(result[i].Data[j].member+'||'+teamName+'||'+result[i].Data[j].sprint_name+'||'+result[i]._id.start_date, [result[i].Data[j].story_points, 0]);
                                            }
                                        }
                                    }
                                }
                            }
                            var membersArr = Array.from(members.entries());
                            for(var i = 0; i < membersArr.length; i++){
                                max++;
                                var id = membersArr[i][0];
                                var information = {
                                    committedVel: membersArr[i][1][0],
                                    completedVel: membersArr[i][1][1]
                                }
                                var rest = [{_id: id, info: information}];
                                db.db("queries").collection("IndividualVelocity").insertMany(rest, function(err, res){
                                if(err){
                                    console.log("Cannot insert");
                                    curr++;
                                    console.log(res);
                                    throw err;
                                }
                                    console.log("Inserted");
                                    curr++;

                                    if(curr == max){
                                        db.close();
                                    }
                                });
                            }
                            


                    });
                }
            });
        }
        }
    });
});