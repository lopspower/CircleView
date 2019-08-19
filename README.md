CircleView
=================

<img src="/preview/preview.gif" alt="sample" title="sample" width="300" height="533" align="right" vspace="52" />

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1f1cf02d760848af8c6b63e7bb0a1db8)](https://app.codacy.com/app/lopspower/CircleView?utm_source=github.com&utm_medium=referral&utm_content=lopspower/CircleView&utm_campaign=Badge_Grade_Dashboard)
<br>
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CircleView-lightgrey.svg?style=flat)](https://android-arsenal.com/details/1/7692)
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)

This is an Android project allowing to realize a circular View in the simplest way possible. Finish the oval shapes of all colors in your projects.

<a href="https://play.google.com/store/apps/details?id=com.mikhaellopez.lopspower">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

USAGE
-----

To make a circular View add CircleView in your layout XML and add CircleView library in your project or you can also grab it via Gradle:

```groovy
implementation 'com.mikhaellopez:circleview:1.2.1'
```

XML
-----

```xml    
<com.mikhaellopez.circleview.CircleView
    android:id="@+id/circleView"
    android:layout_width="300dp"
    android:layout_height="300dp"
    app:cv_border="true"
    app:cv_border_color="#000000"
    app:cv_border_width="8dp"
    app:cv_color="#3f51b5"
    app:cv_shadow="true"
    app:cv_shadow_color="#3f51b5"
    app:cv_shadow_radius="10" />
```

You must use the following properties in your XML to change your CircleView.

| Properties                      | Type                                                         | Default       |
| ------------------------------- | ------------------------------------------------------------ | ------------- |
| `app:cv_color`                  | color                                                        | WHITE         |
| `app:cv_color_start`            | color                                                        | cv_color      |
| `app:cv_color_end`              | color                                                        | cv_color      |
| `app:cv_color_direction`        | left_to_right, right_to_left, top_to_bottom or bottom_to_top | left_to_right |
| `app:cv_border`                 | boolean                                                      | false         |
| `app:cv_border_width`           | dimension                                                    | 4dp           |
| `app:cv_border_color`           | color                                                        | BLACK         |
| `app:cv_border_color_start`     | color                                                        | border_color  |
| `app:cv_border_color_end`       | color                                                        | border_color  |
| `app:cv_border_color_direction` | left_to_right, right_to_left, top_to_bottom or bottom_to_top | left_to_right |
| `app:cv_shadow`                 | boolean                                                      | false         |
| `app:cv_shadow_color`           | color                                                        | BLACK         |
| `app:cv_shadow_radius`          | float                                                        | 8.0f          |
| `app:cv_shadow_gravity`         | center, top, bottom, start or end                            | bottom        |

:information_source: You can also use `android:elevation` instead of `app:cv_shadow` to have default Material Design elevation.

KOTLIN
-----

<img src="/preview/capture.png" alt="sample" title="sample" width="300" height="533" align="right" vspace="200" />

```kotlin
val circleView = findViewById<CircleView>(R.id.circleView)
circleView.apply {
    // Set Color
    circleColor = Color.WHITE
    // or with gradient
    circleColorStart = Color.BLACK
    circleColorEnd = Color.RED
    circleColorDirection = CircleView.GradientDirection.TOP_TO_BOTTOM
    
    // Set Border
    borderWidth = 10f
    borderColor = Color.BLACK
    // or with gradient
    borderColorStart = Color.BLACK
    borderColorEnd = Color.RED
    borderColorDirection = CircleView.GradientDirection.TOP_TO_BOTTOM
    
    // Add Shadow with default param
    shadowEnable = true
    // or with custom param
    shadowRadius = 15f
    shadowColor = Color.RED
    shadowGravity = CircleView.ShadowGravity.CENTER
}
```

JAVA
-----

```java
CircleView circleView = findViewById(R.id.circleView);

// Set Color
circleView.setCircleColor(Color.WHITE);
// or with gradient
circleView.setCircleColorStart(Color.BLACK);
circleView.setCircleColorEnd(Color.RED);
circleView.setCircleColorDirection(CircleView.GradientDirection.TOP_TO_BOTTOM);

// Set Border
circleView.setBorderWidth(10f);
circleView.setBorderColor(Color.BLACK);
// or with gradient
circleView.setBorderColorStart(Color.BLACK);
circleView.setBorderColorEnd(Color.RED);
circleView.setBorderColorDirection(CircleView.GradientDirection.TOP_TO_BOTTOM);

// Add Shadow with default param
circleView.setShadowEnable(true);
// or with custom param
circleView.setShadowRadius(15f);
circleView.setShadowColor(Color.RED);
circleView.setShadowGravity(CircleView.ShadowGravity.CENTER);
```

LICENCE
-----

CircleView by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
