public void getKalorienbedarf(){
    int grundumsatz = Math.round(66.47f + (13.2f + user.getWeight) + (5 * user.getHeight) - (6.8f * user.getAge));
    int leistungsumsatz = Math.round(0.95f * user.getCalorieDegreeOne + 1.2f * user.getCalorieDegreeTwo + 1.45f * user.getCalorieDegreeThree + 1.65f * user.getCalorieDegreeFour + 1.85f * user.getCalorieDegreeFive + 2.2f * user.getCalorieDegreeSix);
    int kalorienbedarf = grundumsatz + leistungsumsatz;
}