var fs = require('fs');
var express = require('express');
var bodyParser = require('body-parser');

var app = express();
app.use(bodyParser.json());

const settings = {
    port: 3000,
    users: './users.json',
    posts: './posts.json'
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
                "height" : req.body.height,
                "weight" : req.body.weight,
                "gender" : req.body.gender,
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
                "beFactor" : req.body.beFactor,
                "calorieDegreeOne" : req.body.calorieDegreeOne,
                "calorieDegreeTwo" : req.body.calorieDegreeTwo,
                "calorieDegreeThree" : req.body.calorieDegreeThree,
                "calorieDegreeFour" : req.body.calorieDegreeFour,
                "calorieDegreeFive" : req.body.calorieDegreeFive,
                "calorieDegreeSix" : req.body.calorieDegreeSix,
                "weightGoal" : req.body.weightGoal,
                "score" : req.body.score
        };
        
        //creat user
        users.splice(numberOfUsers, 0, userToAdd);
        fs.writeFile(settings.users, JSON.stringify(users, null, 2));
        res.status(201).send("User erfolgreich angelegt.");
    });
});



// PUT /users/:userID
app.put('/users/:userID', bodyParser.json(), function(req, res){
  fs.readFile(settings.users, function(err, data){
    var users = JSON.parse(data);
    var currentUser;
    //find the searched user and edit his attribute
    for(var i = 0; i < users.length; i++ ){
      if(users[i].id == req.params.userID){
          currentUser = users[i];
          users[i].firstname = req.body.firstname;
          users[i].name = req.body.name;
          users[i].birthday = req.body.birthday;
          users[i].height = req.body.height;
          users[i].weight = req.body.weight;
          users[i].nickname = req.body.nickname;
          users[i].email = req.body.email;
          users[i].password = req.body.password;
          users[i].dmType = req.body.dmType;
          users[i].insulin = req.body.insulin;
          users[i].tablets = req.body.tablets;
          users[i].unitBZ = req.body.unitBZ;
          users[i].unitKH = req.body.unitKH;
          users[i].lowLimit = req.body.lowLimit;
          users[i].upperLimit = req.body.upperLimit;
          users[i].lowSugar = req.body.lowSugar;
          users[i].upperSugar = req.body.upperSugar;
          users[i].correctionFactor = req.body.correctionFactor;
          users[i].beFactor = req.body.beFactor;
          users[i].calorieDegreeOne = req.body.calorieDegreeOne;
          users[i].calorieDegreeTwo = req.body.calorieDegreeTwo;
          users[i].calorieDegreeThree = req.body.calorieDegreeThree;
          users[i].calorieDegreeFour = req.body.calorieDegreeFour;
          users[i].calorieDegreeFive = req.body.calorieDegreeFive;          
          users[i].calorieDegreeSix = req.body.calorieDegreeSix;
          users[i].weightGoal = req.body.weightGoal;
          users[i].score = req.body.score;
          fs.writeFile(settings.users, JSON.stringify(users, null, 2));
          res.status(200).send("User erfolgreich bearbeitet");
      }
    }
      if(currentUser == null)res.status(400).send("User zum bearbeiten nicht vorhanden.");
  });
});

// DELETE /usersID
app.delete('/users/:userID', function(req, res){
  fs.readFile(settings.users, function(err, data){
    var users = JSON.parse(data);
    var current_i = users.length;

    // Find the position of the searched user and save it in current_i
    for(var i = 0; i < users.length; i++ ){
      if(users[i].id == req.params.userID){
        current_i = i;
      }
    }
    // if current_i is already the same like number of the users, there are no user found. is current_i not the same, delete the user
    if(current_i < users.length){
      users.splice(current_i,1);
      fs.writeFile(settings.users, JSON.stringify(users, null, 2));
      res.status(204).send("User erfolgreich gelöscht");
    } else {
      res.status(404).send("User NOT FOUND");
    }
  });
});


//GET /posts
app.get('/posts', function(req, res){
    fs.readFile(settings.posts,function(err,data){
        var posts = JSON.parse(data);
        res.status(200).send(posts);
    });
});

//GET /posts/postID
app.get('/posts/:postID', function(req, res){
    
    fs.readFile(settings.posts,function(err,data){
        var posts = JSON.parse(data);
        var postID = req.param.postID;
        var current_i;
        var post;
        
        for(var i = 0; i < posts.length; i++ ){
            if(posts[i].id == req.params.postID){
                post = posts[i]
            }
        }
        res.status(200).send(post);
        
    });
    
});


//POST /posts
app.post('/posts', bodyParser.json(), function(req, res){
    fs.readFile(settings.posts, function(err, data){
        var posts = JSON.parse(data);
        var numberOfPosts = posts.length;
        var postIndex = 0;
        
        //id of the last post is inserted into userIndex
            for(var i = 0; i < numberOfPosts; i++){
                if(posts[i].id > postIndex){
                    postIndex = posts[i].id;
                }
            }
        var postToAdd = {
                "id" : ++postIndex,
                "userID" : req.body.userID,
                "userNickname" : req.body.userNickname,
                "text" : req.body.text,
                "date" : req.body.date,
                "time" : req.body.time,
                "rating" : req.body.rating,
                "comments" : req.body.comments
        };
    
        
        //creat post
        posts.splice(numberOfPosts, 0, postToAdd);
        fs.writeFile(settings.posts, JSON.stringify(posts, null, 2));
        res.status(201).send("Post erfolgreich angelegt.");
    });
});

//POST /comment/postID
app.post('/comment/:postID', bodyParser.json(), function(req, res){
    fs.readFile(settings.posts, function(err, data){
        
        
        var posts = JSON.parse(data);
        var postID = req.params.postID;
        var post;
        var postPosition = 0;
        
        for(var i = 0; i < posts.length; i++ ){
            if(posts[i].id == req.params.postID){
                post = posts[i]
                postPosition = i;
            }
        } 
        var numberOfComments = post.comments.length;
        var commentIndex = 0;
        
        //id of the last post is inserted into userIndex
            for(var i = 0; i < numberOfComments; i++){
                if(post.comments[i].id > commentIndex){
                    commentIndex = post.comments[i].id;
                }
            }
        
        var commentToAdd = {
                "id" : ++commentIndex,
                "userID" : req.body.userID,
                "userNickname" : req.body.userNickname,
                "text" : req.body.text,
                "date" : req.body.date,
                "time" : req.body.time,
                "rating" : req.body.rating
            
        }
    
        
        //creat post
        post.comments.splice(numberOfComments, 0, commentToAdd);
        //posts.post[postPosition].comment.splice(numberOfComments,0, commentToAdd);
        
        fs.writeFile(settings.posts, JSON.stringify(posts, null, 2));
        res.status(201).send(post);
    });
});

app.listen(settings.port, function(){
  console.log("Server läuft auf Port " + settings.port + ".");
});