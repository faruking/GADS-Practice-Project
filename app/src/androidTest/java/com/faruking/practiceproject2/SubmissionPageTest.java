package com.faruking.practiceproject2;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnitRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;


@RunWith(AndroidJUnit4.class)
public class SubmissionPageTest {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void submitData(){
        onView(withId(R.id.btnSubmitMain)).perform(click());
        onView(withId(R.id.etFirstName)).perform(typeText("faruku"));
        onView(withId(R.id.etLastName)).perform(typeText("abudugani"));
        onView(withId(R.id.etEmail)).perform(typeText("poli@gmial.com"),closeSoftKeyboard());
       onView(withId(R.id.etProjectLink)).perform(typeText
               ("https://github.com/faruking/GADS-Practice-Project"),closeSoftKeyboard());
        onView(withId(R.id.btnSubmitFinal)).perform(click());
    }
}