package com.guillot.mareu.InstrumentedTest;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.guillot.mareu.R;
import com.guillot.mareu.controler.ListActivity;
import com.guillot.mareu.event.DeleteEvent;
import com.guillot.mareu.utils.DeleteViewAction;
import com.guillot.mareu.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.guillot.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.hamcrest.core.AllOf.allOf;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private static int ITEMS_COUNT = 2;

    @Rule
    public ActivityTestRule<ListActivity> mActivityTestRule =
            new ActivityTestRule<>(ListActivity.class);


    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        onView(allOf(ViewMatchers.withId(R.id.recyclerview_meeting), isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }


    @Test
    public void myMeetingList_filterRoom_shouldFilterByRoom() {
        onView(allOf(withId(R.id.recyclerview_meeting), isDisplayed())).check(withItemCount(ITEMS_COUNT));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Filtrer par Salle")).perform(click());
        onData(CoreMatchers.anything())
                .inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.<View>instanceOf(MenuPopupWindow.MenuDropDownListView.class))
                .atPosition(1)
                .perform(click());

        onView(allOf(withId(R.id.recyclerview_meeting), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myMeetingList_filterDate_shouldFilterByDate() {
        onView(allOf(withId(R.id.recyclerview_meeting), isDisplayed())).check(withItemCount(ITEMS_COUNT));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Filtrer par Date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 07, 01));
        onView(withId(android.R.id.button1)).perform(click());

        onView(allOf(withId(R.id.recyclerview_meeting), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));

    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        onView(allOf(withId(R.id.recyclerview_meeting), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        onView(allOf(withId(R.id.recyclerview_meeting), isDisplayed())).perform(actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withText(R.string.alert)).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
        onView(allOf(withId(R.id.recyclerview_meeting), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myMeetingList_addMeeting_shouldCreateNewMeeting() {
        onView(withId(R.id.fab_add)).perform(click());

        onView(withId(R.id.spinner_meeting)).perform(click());
        onData(anything()).atPosition(2).perform(click());
        onView(withId(R.id.spinner_meeting)).check(matches(withSpinnerText(containsString("Réunion C"))));

        onView(allOf(withParent(withId(R.id.add_constraintlayout)), withId(R.id.button_date))).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 05, 15));
        onView(withId(android.R.id.button1)).perform(click());

        onView(allOf(withParent(withId(R.id.add_constraintlayout)), withId(R.id.button_hour))).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(20, 00));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.textInputEditLayout_topic)).perform(replaceText("Test"));

        onView(withId(R.id.emailEditText)).perform(typeText("emailtest1@gmail.com"), pressImeActionButton());
        onView(withId(R.id.emailEditText)).perform(typeText("emailtest2@gmail.com"), pressImeActionButton());
        onView(withId(R.id.emailEditText)).perform(closeSoftKeyboard());

        onView(withId(R.id.button_validation)).perform(click());

        onView((withId(R.id.recyclerview_meeting))).check(withItemCount(ITEMS_COUNT));
    }
}
