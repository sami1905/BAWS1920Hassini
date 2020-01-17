fs.readFile(settings.users, function(err,data){
    var users = JSON.parse(data);
    var user;
     
    //Gesuchten Benutzer finden
    for(var i = 0; i < users.length; i++){
        if(users[i].id == req.params.userID)
            user = users[i];
    }
    
    var values = [];
    var nValues = 0;
    var nLow = 0;
    var nHigh = 0;
    var nWithinRange = 0;
    
    //Anzahl Blutzuckerwerte ermitteln
    //Anzahl Werte unter Zielbereich
    //Anzahl Werte ueber Zielbereich
    //Anzahl Werte im Zielbereich
    //Alle Blutzuckerwerte in eine Variable speichern.
    for(var i = 0; i < user.diary.events.length; i++){
        if(user.diary.events[i].sugar != 0){
            nValues++;
            values.push(user.diary.events[i].sugar)
        }
        if(user.diray.events[i].sugar < user.lowLimit){
            nLow++;
        }
        if(user.diray.events[i].sugar > user.upperLimit){
            nHigh++;
        }
        if(user.diray.events[i].sugar >= user.lowLimit && user.diray.events[i].sugar <= user.upperLimit){
            nWithinRange++;
        }
    }
   
    //Prozentualer Anteil der Blutzuckerwerte unter, ueber und innerhalb des Zielbereiches
    var percentLow = 0;
    var percentWithinRange = 0;
    var percentHigh = 0;
    
    percentLow = (nLow/nValues);
    percentWithinRange = (nWithinRange/nValues);
    percentHigh = (nHigh/nValues);
    
    //Durschnittlicher Blutzucker berechnen
    var averageValue = 0;
    for (var i = 0; i < values.length; i++){
        averageValue = averageValue + values[i];
    }
    averageValue = averageValue/nValues;
    
    //HbA1c-Wert berechnen
    var hba1c = 0;
    hba1c = ((0.031 * averageValue) + 2.393);
    
    //Statistiken in Variable speichern und an den Client senden
    var statistics = [];
    statistics.pus({
        "nValues" : nValues,
        "nLow" : nLow,
        "nHigh" : nHigh,
        "nWithinRange" : nWithinRange,
        "percentLow" : percentLow,
        "percentHigh" : percentHigh,
        "percentWithinRange" : percentWithinRange,
        "averageValue" : averageValue,
        "hba1c" : hba1c
    });
    

    if(nValues != 0){
        res.status(200).send(statistics);
    }
    else res.status(500).send("error in database");
});   