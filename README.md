CircleView
=================

<img src="/preview/preview.gif" alt="sample" title="sample" width="300" height="533" align="right" vspace="52" />

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)

This is an Android project allowing to realize a circular View in the simplest way possible.

<a href="https://play.google.com/store/apps/details?id=com.mikhaellopez.lopspower">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

USAGE
-----

To make a circular View add CircleView in your layout XML and add CircleView library in your project or you can also grab it via Gradle:

```groovy
implementation 'com.mikhaellopez:circleview:1.0.2'
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


##### Properties:

* `app:cv_color`                          (color)     -> default WHITE
* `app:cv_border`                         (boolean)   -> default false
* `app:cv_border_width`                   (dimension) -> default 4dp
* `app:cv_border_color`                   (color)     -> default BLACK
* `app:cv_shadow`                         (boolean)   -> default false
* `app:cv_shadow_color`                   (color)     -> default BLACK
* `app:cv_shadow_radius`                  (float)     -> default 8.0f
* `app:cv_shadow_gravity`                 (center, top, bottom, start or end) -> default bottom

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
