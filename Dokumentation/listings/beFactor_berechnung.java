private TextWatcher beTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String carbohydrateInput = editTextCarbohydrates.getText().toString().trim();
            if(!carbohydrateInput.isEmpty()){
                float be = Float.valueOf(carbohydrateInput);
                be = be/12;

                editTextBe.setText(Float.toString(be));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
};