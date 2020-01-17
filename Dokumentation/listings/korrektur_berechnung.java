private TextWatcher correctionTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String valueInput = editTextValue.getText().toString().trim();
            if(!valueInput.isEmpty() && Integer.parseInt(valueInput) >= user.getUpperSugar){
                float correction = Float.valueOf(valueInput);
                correction -= 100;
                correction = correction/user.getCorrectionFactor;

                editTextCorrection.setText(Float.toString(correction));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {


        }
};