# Tiny Keyboard

<img src="images/keyboard.png"
      alt="closeup"
      width="500"/>

## About

- Smallest possible APK size of 22kB (as of version 0.3)
- Permissions: 0
- Supported layouts: en_US
- No Launcher icon and Settings
- Licensed under Apache License Version 2

## How it's made

Android OS contains a default [Keyboard](https://developer.android.com/reference/android/inputmethodservice/Keyboard) and [KeyboardView](https://developer.android.com/reference/android/inputmethodservice/KeyboardView) implementations (deprecated os of Android 10, but still available). Input method developers can use these classes as base for their own keyboard implementations. Tiny Keyboard is an implementation without any changes.

All that is contained in application source is key layouts and special handling for action keys.

## Downloads

[<img src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png"
      alt="Get it on Google Play"
      height="80">](https://play.google.com/store/apps/details?id=rkr.tinykeyboard.inputmethod)
      
## Credits

Based on https://android.googlesource.com/platform/development/+/master/samples/SoftKeyboard

AOSP Keyboard.java: https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/inputmethodservice/Keyboard.java

AOSP KeyboardView.java: https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/inputmethodservice/KeyboardView.java
