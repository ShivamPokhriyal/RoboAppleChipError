# RoboAppleChipError
Demonstrate an issue with Robolectric on Apple M1 chip


# Project Description

This project is a minimal reproducible sample to demonstrate an issue with using `ShadowLooper.runUiThreadTasksIncludingDelayedTasks()` in Apple M1 devices. 
Below is the folder structure of this sample

```
- main
  - SampleView.kt (Custom View class to be tested)
  
- test
  - SampleViewTest.kt (Class which runs tests using Robolectric)

```

# Issue Description

`SampleView` is a simple `EditText` which has a `TextWatcher` and notifies the attached `listener`(after a delay of 200 ms) whenever there's a change in the EditText. 
It uses View's `postDelayed` construct to notify the listener after a delay of 200 ms. 


`ShadowLooper.runUiThreadTasksIncludingDelayedTasks()` is a robolectric utility that runs all runnable tasks (pending and future) that have been queued on the UI thread. 
I'm using this utility to test around `postDelayed` construct inside the `SampleView`. 

## Note
*NOTE* : This test successfully passes on a Mac with intel chip, but fails only on a Mac with M1 chip. 

# How to run

Clone the project and run `./gradlew :app:testDebugUnitTest --info`. 
Or after cloning, open the project in AndroidStudio and search for `SampleViewTest` class and run the test. 

# Expected Result

`ShadowLooper.runUiThreadTasksIncludingDelayedTasks()` should wait for the postDelayed construct to finish execution before running the next step in the test. 
The test should run successfully on Apple M1 Macbook just like it's working fine on an intel chip macbook. 

# Observed Result/Outcome

Test fails on Apple M1 device(passes on intel chip) 
```

com.example.roboapplechiperror.SampleViewTest > testView FAILED
    value of: getCalled()
    expected to be true
        at com.example.roboapplechiperror.SampleViewTest.testView(SampleViewTest.kt:23)

1 test completed, 1 failed

```


# Workaround

Annotate tests with `@LooperMode(LooperMode.Mode.LEGACY)` fixes the issue on Apple M1 devices. 
