CircleView
=================

<img src="/preview/preview.gif" alt="sample" title="sample" width="300" height="533" align="right" vspace="52" />

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1f1cf02d760848af8c6b63e7bb0a1db8)](https://app.codacy.com/app/lopspower/CircleView?utm_source=github.com&utm_medium=referral&utm_content=lopspower/CircleView&utm_campaign=Badge_Grade_Dashboard)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
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
implementation 'com.mikhaellopez:circleview:1.1.1'
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

| Properties              | Type                              | Default |
| ----------------------- | --------------------------------- | ------- |
| `app:cv_color`          | color                             | WHITE   |
| `app:cv_border`         | boolean                           | false   |
| `app:cv_border_width`   | dimension                         | 4dp     |
| `app:cv_border_color`   | color                             | BLACK   |
| `app:cv_shadow`         | boolean                           | false   |
| `app:cv_shadow_color`   | color                             | BLACK   |
| `app:cv_shadow_radius`  | float                             | 8.0f    |
| `app:cv_shadow_gravity` | center, top, bottom, start or end | bottom  |

:information_source: You can also use `android:elevation` instead of `app:cv_shadow` to have default Material Design elevation.

KOTLIN
-----

<img src="/preview/capture.png" alt="sample" title="sample" width="300" height="533" align="right" vspace="200" />

```kotlin
val circleView = findViewById<CircleView>(R.id.circleView)
circleView.circleColor = Color.WHITE
// Set Border
circleView.borderColor = Color.BLACK
circleView.borderWidth = 10f
// Add Shadow with default param
circleView.shadowEnable = true
// or with custom param
circleView.shadowRadius = 15f
circleView.shadowColor = Color.RED
circleView.shadowGravity = CircleView.ShadowGravity.CENTER
```

JAVA
-----

```java
CircleView circleView = findViewById(R.id.circleView);
circleView.setCircleColor(Color.WHITE);
// Set Border
circleView.setBorderColor(Color.BLACK);
circleView.setBorderWidth(10f);
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
