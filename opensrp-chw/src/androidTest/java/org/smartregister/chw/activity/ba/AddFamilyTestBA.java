package org.smartregister.chw.activity.ba;


import android.Manifest;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import com.vijay.jsonwizard.activities.JsonFormActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.smartregister.chw.R;
import org.smartregister.chw.activity.LoginActivity;
import org.smartregister.chw.activity.utils.Constants;
import org.smartregister.chw.activity.utils.OrderedRunner;
import org.smartregister.chw.activity.utils.Utils;

import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.smartregister.chw.activity.utils.Utils.getViewId;


@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(OrderedRunner.class)
public class AddFamilyTestBA {

    @Rule
    public ActivityTestRule<LoginActivity> intentsTestRule = new ActivityTestRule<>(LoginActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.CALL_PHONE);
    @Rule
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.CAMERA);
    @Rule
    public GrantPermissionRule mRuntimePermissionRule1 = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);
    Utils utils = new Utils();

    private static Matcher<View> withError(final String expected) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof EditText) {
                    return ((EditText) item).getError().toString().equals(expected);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Not found error message" + expected + ", find it!");
            }
        };
    }

    @Before
    public void setUp() throws InterruptedException {
        utils.logIn(Constants.BoreshaAfyaConfigs.ba_username, Constants.BoreshaAfyaConfigs.ba_password);
        Thread.sleep(5000);
    }

    Activity getCurrentActivity() throws Throwable {
        getInstrumentation().waitForIdleSync();
        final Activity[] activity = new Activity[1];
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                java.util.Collection<Activity> activities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                activity[0] = Iterables.getOnlyElement(activities);
            }
        });
        return activity[0];
    }

    @Test
    public void addfamily() throws Throwable {

        onView(withId(R.id.action_register))
                .perform(click());

        Activity activity = getCurrentActivity();

        onView(withId(getViewId((JsonFormActivity) activity, "step1:fam_name")))
                .perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(5))
                .perform(typeText("Another"), closeSoftKeyboard());
        // onView(withText("Village *")).perform(typeText("Another"), closeSoftKeyboard());
        onView(withId(7))
                .perform(typeText("Fig tree 5 meters North west of the house"), closeSoftKeyboard());
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Record location"))
                .perform(click());
        Thread.sleep(1000);
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("OK"))
                .perform(click());
        Thread.sleep(1000);
        //onView(withHint("Nearest health facility")).perform(scrollTo(), click());
        onView(withId(13))
                .perform(scrollTo(), click());
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Kabila Village"))
                .perform(doubleClick());
        Thread.sleep(3000);
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Next"))
                .perform(click());

        //onView(ViewMatchers.withText("Family name")).check(matches(isDisplayed()));
        Thread.sleep(1000);
        onView(withId(18)).perform(doubleClick())
                .perform(typeText("TestName"), closeSoftKeyboard());
        onView(withId(20)).perform(doubleClick())
                .perform(typeText("MiddleName"), closeSoftKeyboard());
        onView(withId(24)).perform(doubleClick())
                .perform(click());
        Thread.sleep(1000);
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("done"))
                .perform(click());
        Thread.sleep(500);
        onView(withId(58)).perform(scrollTo())
                .perform(typeText("1234567890"), closeSoftKeyboard());
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Health insurance provider"))
                .perform(scrollTo(), click());
        Thread.sleep(500);
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Jubilee Insurance"))
                .perform(click());
        Thread.sleep(500);
        //onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Health insurance provider")).perform(scrollTo());
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Sex"))
                .perform(scrollTo(), click());
        Thread.sleep(500);
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Male"))
                .perform(click());
        Thread.sleep(500);
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Physical disabilities"))
                .perform(scrollTo(), click());
        Thread.sleep(500);
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("No"))
                .perform(click());
        onView(withHint("Phone number")).perform(scrollTo())
                .perform(typeText("0721137826"), closeSoftKeyboard());
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Farmer"))
                .perform(scrollTo(), click());
        onView(androidx.test.espresso.matcher.ViewMatchers.withSubstring("Traditional leader"))
                .perform(scrollTo(), click());
        Thread.sleep(500);

    }


}
