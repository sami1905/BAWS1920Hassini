private TextWatcher insulinUnitsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            float be = editTextBe.getText().toString().trim();
            String correctionInput = editTextCorrection.getText().toString().trim();
            if(!beInput.isEmpty() && !correctionInput.isEmpty()){
                float insulinUnits = Float.valueOf(correctionInput);
                float be = Float.valueOf(beInput);
                insulinUnits = insulinUnits + (be * user.getBeFactor);

                editTextInsulinUnits.setText(Float.toString(insulinUnits));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
};