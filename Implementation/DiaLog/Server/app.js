var express = require('express');
var bodyParser = require('body-parser');

var app = express();
app.use(bodyParser.json());

const settings = {
    port: 3000,
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

app.listen(settings.port, function(){
  console.log("Server läuft auf Port " + settings.port + ".");
});