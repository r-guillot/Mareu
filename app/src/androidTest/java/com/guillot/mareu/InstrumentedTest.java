package com.guillot.mareu;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.guillot.mareu.controler.ListActivity;
import com.guillot.mareu.event.DeleteEvent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    private ListActivity mActivity;

    private static int ITEMS_COUNT = 2;

//    @Rule
//    public ActivityTestRule<ListActivity> mActivityRule =
//            new ActivityTestRule<>(ListActivity.class);

    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        onView(allOf(withId(R.id.activity_list),isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
    }
}
