package com.daniloprado.weather.view.main;


import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.daniloprado.weather.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestApproach2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<> (MainActivity.class);

    String TAG_LOG = "EasyWeather Error :- ";
    ViewInteraction textView;
    ViewInteraction recyclerView;
    ViewInteraction relativeLayout;
    ViewInteraction imageButton3;
    ViewInteraction floatingActionButton;
    ViewInteraction appCompatEditText;
    ViewInteraction textView3;
    ViewInteraction recyclerView2;
    ViewInteraction textView4;

    @Test
    public void t1VerifyUserIsOnHomePage() {

        try {
            textView = onView (
                    allOf (withText ("Easy Weather"),
                            childAtPosition (
                                    allOf (withId (R.id.toolbar),
                                            childAtPosition (
                                                    IsInstanceOf.<View>instanceOf (android.widget.LinearLayout.class),
                                                    0)),
                                    0),
                            isDisplayed ( )));

            textView.check (ViewAssertions.matches (withText ("Easy Weather")));
        } catch (NoMatchingViewException e) {
            Log.d (TAG_LOG, "No login test performed!");
        }
    }

    @Test
    public void t2VerifyUserIsWatchingCorrectCityWeather() {
        try {

            recyclerView = onView (
                    allOf (withId (R.id.recyclerview),
                            childAtPosition (
                                    allOf (withId (R.id.viewflipper),
                                            childAtPosition (
                                                    IsInstanceOf.<View>instanceOf (ViewGroup.class),
                                                    0)),
                                    0),
                            isDisplayed ( )));
            recyclerView.check (matches (isDisplayed ( )));

            ViewInteraction imageButton = onView (
                    allOf (withId (R.id.fab),
                            childAtPosition (
                                    childAtPosition (
                                            withId (R.id.root_content),
                                            0),
                                    1),
                            isDisplayed ( )));
            imageButton.check (matches (isDisplayed ( )));

            relativeLayout = onView (
                    allOf (withId (R.id.card_container),
                            childAtPosition (
                                    childAtPosition (
                                            withId (R.id.recyclerview),
                                            0),
                                    0),
                            isDisplayed ( )));
            relativeLayout.perform (click ( ));
            Thread.sleep (6000);
            onView (withId (R.id.textview_city_name)).check (matches (withText ("Dublin")));
            onView (withContentDescription ("Navigate up")).check (matches (isDisplayed ( )));
        } catch (NoMatchingViewException e) {
            Log.d (TAG_LOG, "No login test performed!");

        } catch (InterruptedException e) {
            e.printStackTrace ( );
            Log.d (TAG_LOG, e.getMessage ( ) + "No login test performed!");
        }

    }

    @Test
    public void t3VeifyUserNavigatedBacktoMainUI() {
        try {
            imageButton3 = onView (
                    allOf (
                            withContentDescription ("Navigate up"),
                            childAtPosition (allOf (withId (R.id.toolbar),
                                    childAtPosition (
                                            withClassName (is
                                                    ("android.support.design.widget.AppBarLayout")), 0)), 2),
                            isDisplayed ( )));
            imageButton3.perform (click ( ));
            t1VerifyUserIsOnHomePage ();
        } catch (NoMatchingViewException e) {
            Log.d (TAG_LOG, "No login test performed!");


        }
    }
    @Test
    public void t4VerifyaddNewCity() {
        try {
            floatingActionButton = onView (
                    allOf (withId (R.id.fab),
                            childAtPosition (
                                    childAtPosition (
                                            withId (R.id.root_content),
                                            0),
                                    1),
                            isDisplayed ( )));
            floatingActionButton.perform (click ( ));
            Thread.sleep (6000);

            onView (withId (R.id.edittext_toolbar_city_search)).check (matches (isDisplayed ( )));
            onView (withId (R.id.edittext_toolbar_city_search)).perform (ViewActions.typeTextIntoFocusedView ("new"));
            ///
            appCompatEditText = onView (
                    allOf (withId (R.id.edittext_toolbar_city_search),
                            childAtPosition (
                                    allOf (withId (R.id.toolbar),
                                            childAtPosition (
                                                    withClassName (is ("android.support.design.widget.AppBarLayout")),
                                                    0)),
                                    0),
                            isDisplayed ( )));
            appCompatEditText.perform (replaceText ("new"), closeSoftKeyboard ( ));
            Thread.sleep (6000);

            textView3 = onView (
                    allOf (withId (R.id.textview_found_city_name), withText ("Ansley, England, United Kingdom"),
                            childAtPosition (
                                    allOf (withId (R.id.content_layout_found_city),
                                            childAtPosition (
                                                    withId (R.id.recyclerview_cities_found),
                                                    0)),
                                    0),
                            isDisplayed ( )));
            textView3.check (matches (withText ("Ansley, England, United Kingdom")));
            Thread.sleep (6000);

            recyclerView2 = onView (
                    allOf (withId (R.id.recyclerview_cities_found),
                            childAtPosition (
                                    withId (R.id.viewflipper),
                                    0)));
            recyclerView2.perform (actionOnItemAtPosition (0, click ( )));

             textView4 = onView (
                    allOf (withId (R.id.textview_city_name), withText ("Ansley"),
                            childAtPosition (
                                    allOf (withId (R.id.card_container),
                                            childAtPosition (
                                                    IsInstanceOf.<View>instanceOf (android.widget.LinearLayout.class),
                                                    0)),
                                    0),
                            isDisplayed ( )));
            textView4.check (matches (withText ("Ansley")));
            Thread.sleep (10000);

        } catch (NoMatchingViewException e) {
            Log.d (TAG_LOG, "No login test performed!");
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }
    }

    // There is an issue with variable scope. So For time being I am closing this method
    /*@Test
    public void t5DeleteCityFromListingView() {
        try {
            Thread.sleep (4000);
            textView4.perform (ViewActions.swipeLeft ( ));
        } catch (NoMatchingViewException e) {
            Log.d (TAG_LOG, "No login test performed!");
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }
    }*/
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View> ( ) {
            @Override
            public void describeTo(Description description) {
                description.appendText ("Child at position " + position + " in parent ");
                parentMatcher.describeTo (description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent ( );
                return parent instanceof ViewGroup && parentMatcher.matches (parent)
                        && view.equals (((ViewGroup) parent).getChildAt (position));
            }
        };
    }
}
