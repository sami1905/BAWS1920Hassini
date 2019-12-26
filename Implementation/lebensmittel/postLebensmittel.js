var fs = require('fs');
var express = require('express');
var bodyParser=require('body-parser');

var app = express();
app.use(bodyParser.json());

const settings = {
  port: process.env.PORT || 3000,
  database: './lebensmittel.json'
};

app.use(function(err, req, res, next){
  console.log(err.stack);
  res.end(err.status + ' ' + err.messages);
});

app.use(function(req, res, next){
  console.log('Time ' + Date.now() + ' | Request-Pfad: ' + req.path);
  next();
});


// POST /lebensmittel
app.post('/lebensmittel', bodyParser.json(), function(req, res){
  fs.readFile(settings.database, function(err, data){
    var lebensmittel = JSON.parse(data);
    var max_index = 0;
    var current_i = lebensmittel.length;

    // id of the last user is inserted into max_index
    for(var i = 0; i < lebensmittel.length; i++){
      if(lebensmittel[i].id > max_index){
        max_index = lebensmittel[i].id;
      }
    }

    // Check if username is already assigned. Print an error or add an new user
    for(var i = 0; i < lebensmittel.length; i++){
      if(lebensmittel[i].name == req.body.name){
        current_i = i;
      }
    }
    if(current_i < lebensmittel.length){
      res.status(409).send("Lebensmittel ist schon vergeben");
    } else {
      lebensmittel.push({
        "id": ++max_index,
        "name": req.body.name,
        "category": req.body.category,
        "amount": req.body.amount,
				"kcal": req.body.kcal,
        "kj": req.body.kj,
        "e": req.body.e,
        "kh": req.body.kh,
				"f": req.body.f,
        "unit": req.body.unit
      });
      fs.writeFile(settings.database, JSON.stringify(lebensmittel, null, 2));
      res.status(201).send("Lebensmittel erfolgreich gespeichert!\n");
    }
  });
})

app.listen(settings.port, function(){
  console.log("Dienstgeber läuft auf Port " + settings.port + ".");
});