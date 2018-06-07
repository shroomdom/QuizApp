/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.quizapp
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

/**
 * This app displays a quiz and returns a user's score.
 */
public class MainActivity extends AppCompatActivity {

    int score = 0;

    /**
     * Prevents score from resetting after view rotation.
     */
    private static final String answerPoints = "totalPoints";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button answerButton = (Button) findViewById(R.id.answer_button);

        answerButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                score = 0;
                String tempAnswer = submitAnswers();

                Toast.makeText(getApplicationContext(),
                        tempAnswer, Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * This method is called when the Submit Answers button is clicked.
     */
    public String submitAnswers(){

        String name = "";
        name = questionOne();

        String displayName = "";
        displayName = generateName(name);

        String q2CheckedAnswer = "";
        q2CheckedAnswer = questionTwo();

        String answerQ3 = "";
        answerQ3 = questionThree();

        String answerQ4 = "";
        answerQ4 = questionFour();

        String answerQ5 = "";
        answerQ5 = questionFive();

        String hiMessage = "Hi, " + displayName;

        String finalScore = "You got " + score + " out of 5 questions correct.";

        String  answerResults = "Your Answers:";
                answerResults += "\n Q1: " + name;
                answerResults += "\n Q2: " + q2CheckedAnswer;
                answerResults += "\n Q3: " + answerQ3;
                answerResults += "\n Q4: " + answerQ4;
                answerResults += "\n Q5: " + answerQ5;

        String finalResults = hiMessage + "\n" + finalScore + "\n"
                + answerResults + "\n" + displayAnswerKey();

        return finalResults;
    }

    private String questionOne(){

        // These 2 lines get the user's answer from Question 1.
        EditText nameField = (EditText) findViewById(R.id.q1_answer_field);
        String name = nameField.getText().toString();

        return name;
    }

    private String generateName(String name){

        String displayName = "";

        if (name.equals("")){
            displayName = "No Name";
        } else {
            displayName = name;
            score++;
        }

        return displayName;
    }

    private String questionTwo(){

        // Gets the ID of selected button from Question 2.
        RadioGroup q2RadioGroup = (RadioGroup) findViewById(R.id.q2_radio_choices);
        int selectedQ2Id = q2RadioGroup.getCheckedRadioButtonId();

        String q2CheckedAnswer = "";

         //If user didn't select any radio buttons, selectedQ2ID is returned as value -1.
        if (selectedQ2Id == -1) {
            q2CheckedAnswer = "Answer not selected.";
        } else {
            // Gets button view called RadioButton from activity_main.xml file for Question 2.
            RadioButton checkedQ2button = (RadioButton) findViewById(selectedQ2Id);

            // Gets text from selected button for Question 2.
            q2CheckedAnswer = checkedQ2button.getText().toString();

            if (q2CheckedAnswer.equals("Their baby")){
                score++;
            }
        }
        return q2CheckedAnswer;
    }

    private String questionThree(){

        // These 2 lines get the user's answer from Question 3.
        EditText answerFieldQ3 = (EditText) findViewById(R.id.q3_answer_field);
        String answerQ3 = answerFieldQ3.getText().toString();

        if (answerQ3.equals("13112221")){
            score++;
        }

        return answerQ3;
    }

    private String questionFour(){

        // These 2 lines check if the one check box is checked from Question 4.
        CheckBox oneCheckBox = (CheckBox) findViewById(R.id.one_checkbox);
        boolean hasOneBoxChecked = oneCheckBox.isChecked();

        // These 2 lines check if the two check box is checked from Question 4.
        CheckBox twoCheckBox = (CheckBox) findViewById(R.id.two_checkbox);
        boolean hasTwoBoxChecked = twoCheckBox.isChecked();

        // These 2 lines check if the 89 check box is checked from Question 4.
        CheckBox eightynineCheckBox = (CheckBox) findViewById(R.id.eightynine_checkbox);
        boolean hasEightynineBoxChecked = eightynineCheckBox.isChecked();

        // These 2 lines check if the 97 check box is checked from Question 4.
        CheckBox ninetysevenCheckBox = (CheckBox) findViewById(R.id.ninetyseven_checkbox);
        boolean hasNinetysevenBoxChecked = ninetysevenCheckBox.isChecked();

        if (hasTwoBoxChecked && hasEightynineBoxChecked && hasNinetysevenBoxChecked && (hasOneBoxChecked == false)){
            score++;
        }

        String answerQ4 = valueOfCheckBox(hasOneBoxChecked, hasTwoBoxChecked,
                hasEightynineBoxChecked, hasNinetysevenBoxChecked);

        return answerQ4;
    }

    private String questionFive(){

        // These 2 lines get the user's answer from Question 5.
        EditText answerFieldQ5 = (EditText) findViewById(R.id.q5_answer_field);
        String answerQ5 = answerFieldQ5.getText().toString();

        if (answerQ5.equals("mushroom") || answerQ5.equals("Mushroom")){
            score++;
        }

        return answerQ5;
    }

    private String valueOfCheckBox(Boolean hasOneBoxChecked, Boolean hasTwoBoxChecked,
                                   Boolean hasEightynineBoxChecked, Boolean hasNinetysevenBoxChecked) {
        String valueOfOneBox = "";

        if (hasOneBoxChecked) {
            valueOfOneBox = "1 ";
        }

        if (hasTwoBoxChecked) {
            valueOfOneBox += "2 ";
        }

        if (hasEightynineBoxChecked) {
            valueOfOneBox += "89 ";
        }

        if (hasNinetysevenBoxChecked) {
            valueOfOneBox += "97";
        }

        return valueOfOneBox;
    }

    private String displayAnswerKey(){
        String  answerKey = "Answer Key:";
        answerKey += "\n Q1: Your name";
        answerKey += "\n Q2: Their baby";
        answerKey += "\n Q3: 13112221";
        answerKey += "\n Q4: 2, 89, 97";
        answerKey += "\n Q5: mushroom";
        return answerKey;
    }
}