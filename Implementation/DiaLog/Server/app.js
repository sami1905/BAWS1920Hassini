var fs = require('fs');
var express = require('express');
var bodyParser = require('body-parser');

var app = express();
app.use(bodyParser.json());

const settings = {
    port: 3000,
    users: './users.json'
};

app.use(function(err, req, res, next){
  console.log(err.stack);
  res.end(err.status + ' ' + err.messages);
});

app.use(function(req, res, next){
  console.log('Time ' + Date.now() + ' | Request-Pfad: ' + req.path);
  next();
});



//GET /
app.get('/helloWorld', bodyParser.json(), function(req, res){
   res.status(200).send("Server ist erreichbar!");
});

//GET /users
app.get('/users', function(req, res){
    fs.readFile(settings.users,function(err,data){
        var users = JSON.parse(data);
        res.status(200).send(users);
    });
});


//POST /users
app.post('/users', bodyParser.json(), function(req, res){
    fs.readFile(settings.users, function(err, data){
        var users = JSON.parse(data);
        var numberOfUsers = users.length;
        var userIndex = 0;
        
        //id of the last user is inserted into userIndex
            for(var i = 0; i < numberOfUsers; i++){
                if(users[i].id > userIndex){
                    userIndex = users[i].id;
                }
            }
        var userToAdd = {
                "id" : ++userIndex,
                "firstname" : req.body.firstname,
                "name" : req.body.name,
                "birthday" : req.body.birthday,
                "age" : req.body.age,
                "height" : req.body.height,
                "weight" : req.body.weight,
                "genger" : req.body.genger,
                "nickname" : req.body.nickname,
                "email" : req.body.email,
                "password" : req.body.password,
                "dmType" : req.body.dmType,
                "insulin" : req.body.insulin,
                "tablets" : req.body.tablets,
                "unitBZ" : req.body.unitBZ,
                "unitKH" : req.body.unitKH,
                "lowLimit" : req.body.lowLimit,
                "upperLimit" : req.body.upperLimit,
                "lowSugar" : req.body.lowSugar,
                "upperSugar" : req.body.upperSugar,
                "correctionFactor" : req.body.correctionFactor,
                "beFactor" : req.body.beFactor
        };
        
        //creat user
        users.splice(numberOfUsers, 0, userToAdd);
        fs.writeFile(settings.users, JSON.stringify(users, null, 2));
        res.status(201).send("Benutzer erfolgreich hinzugefügt.");
    });
});

app.listen(settings.port, function(){
  console.log("Server läuft auf Port " + settings.port + ".");
});