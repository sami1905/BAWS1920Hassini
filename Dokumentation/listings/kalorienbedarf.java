public void getKalorienbedarf(){
    int grundumsatz = Math.round(66.47f + (13.2f + user.getWeight) + (5 * user.getHeight) - (6.8f * user.getAge));

    float anzahlStundenAktivitaet = user.getCalorieDegreeOne + user.getCalorieDegreeTwo + user.getCalorieDegreeThree + user.getCalorieDegreeFour + user.getCalorieDegreeFive + user.getCalorieDegreeSix;

    int leistungsumsatz = Math.round((0.95f * user.getCalorieDegreeOne / anzahlStundenAktivitaet) + (1.2f * user.getCalorieDegreeTwo / anzahlStundenAktivitaet) + (1.45f * user.getCalorieDegreeThree / anzahlStundenAktivitaet) + (1.65f * user.getCalorieDegreeFour / anzahlStundenAktivitaet) + (1.85f * user.getCalorieDegreeFive / anzahlStundenAktivitaet) + (2.2f * user.getCalorieDegreeSix / anzahlStundenAktivitaet));

    int kalorienbedarf = grundumsatz * leistungsumsatz;
}
