package com.example.konversisuhu;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerFrom, spinnerTo;
    EditText editTextNumber;
    Button buttonConvert, buttonReset; // Added buttonReset here for consistency
    TextView textViewResult;
    ImageView imageThermometer;

    String[] units = {"Celcius", "Fahrenheit", "Kelvin", "Reamur"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);
        imageThermometer = findViewById(R.id.imageThermometer);
        buttonReset = findViewById(R.id.buttonReset); // Initialize buttonReset

        // Set up the Reset button click listener
        buttonReset.setOnClickListener(v -> {
            editTextNumber.setText(""); // Clear the input field
            textViewResult.setText("Hasil: -"); // Reset the result text
            // Optional: Reset spinner selections to default if desired
            // spinnerFrom.setSelection(0);
            // spinnerTo.setSelection(0);
        });

        // Load rotation animation for the thermometer image
        Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        // Note: You need to create res/anim/rotate.xml if you haven't already
        // Example rotate.xml:
        /*
        <?xml version="1.0" encoding="utf-8"?>
        <set xmlns:android="http://schemas.android.com/apk/res/android"
            android:shareInterpolator="false">
            <rotate
                android:duration="1000"
                android:fromDegrees="0"
                android:pivotX="50%"
                android:pivotY="50%"
                android:toDegrees="360" />
        </set>
        */


        // Set up ArrayAdapter for the Spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Set up the Convert button click listener
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = editTextNumber.getText().toString().trim(); // Trim whitespace

                // Validate input
                if (inputStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Mohon masukkan suhu terlebih dahulu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double input = Double.parseDouble(inputStr);
                    String from = spinnerFrom.getSelectedItem().toString();
                    String to = spinnerTo.getSelectedItem().toString();

                    double result;

                    // If "from" and "to" units are the same, no conversion is needed
                    if (from.equals(to)) {
                        result = input;
                        Toast.makeText(MainActivity.this, "Unit asal dan tujuan sama.", Toast.LENGTH_SHORT).show();
                    } else {
                        result = convertTemperature(input, from, to);
                    }

                    String unitSymbol = getUnitSymbol(to);
                    textViewResult.setText("Hasil: " + String.format("%.2f", result) + " " + unitSymbol);

                    // Start thermometer animation
                    imageThermometer.startAnimation(rotateAnim);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Input suhu tidak valid. Masukkan angka.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Converts temperature from one unit to another.
     * @param value The temperature value to convert.
     * @param from The original unit (e.g., "Celcius", "Fahrenheit").
     * @param to The target unit (e.g., "Kelvin", "Reamur").
     * @return The converted temperature value.
     */
    private double convertTemperature(double value, String from, String to) {
        double celsius; // Intermediate conversion to Celsius

        // Convert the input value to Celsius first
        switch (from) {
            case "Fahrenheit":
                celsius = (value - 32) * 5 / 9;
                break;
            case "Kelvin":
                celsius = value - 273.15;
                break;
            case "Reamur":
                celsius = value * 5 / 4;
                break;
            default: // "Celcius"
                celsius = value;
        }

        // Convert from Celsius to the target unit
        switch (to) {
            case "Fahrenheit":
                return (celsius * 9 / 5) + 32;
            case "Kelvin":
                return celsius + 273.15;
            case "Reamur":
                return celsius * 4 / 5;
            default: // "Celcius"
                return celsius;
        }
    }

    /**
     * Returns the appropriate unit symbol for a given temperature unit.
     * @param unit The temperature unit (e.g., "Fahrenheit", "Kelvin").
     * @return The unit symbol (e.g., "°F", "K").
     */
    private String getUnitSymbol(String unit) {
        switch (unit) {
            case "Fahrenheit": return "°F";
            case "Kelvin": return "K";
            case "Reamur": return "°R";
            default: return "°C"; // Default to Celsius symbol
        }
    }
}