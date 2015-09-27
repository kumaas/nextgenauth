## Keystroke-Biometric Authenticator for Android ##

### Concept ###

* Numeric code based authentication schemes can easily lead to stolen credentials.
* Two-factor authentication is cumbersome.
* We have come up with a model which with further improvements, may change authentication works in future.

Early on, we learned from our experiments that if we plotted the screen coordinates at which users touched various numbers on a numeric keyboard on a touchscreen, one would obtain a Gaussian distribution, such as below. The X coordinate variation is shown for various keys on a numeric on-screen keyboard for a single user. 

![x-axis.png](https://bitbucket.org/repo/B6beke/images/3900933863-x-axis.png)

Our idea is to use the following signals to build the unique fingerprint of a user, while asking him/her to train the model and then using the model to authenticate the user after that. A major merit of this method is that one is not constrained to using a single passcode/password to authenticate the user. This additionally also gives an extra layer of security over passcodes, because emulating one's use of an electronic device is rather challenging.

We looked at the following signals to generate a unique fingerprint and model for a particular user:
* Typing heatmap (spatial coordinates of points that the user struck)
* Pressure
* Size of contact with the touchscreen
* Duration of contact with the touchscreen 

## Implementation ##

Our application is implemented using Android because it provided to us, APIs that allowed us to query the aforementioned signals, and because we felt that the variety in Android devices would let us test the efficacy of our handpicked signals for the purpose of authentication.

The main screen of the application is shown below:

![training_application.png](https://bitbucket.org/repo/B6beke/images/2872883611-training_application.png)

### What is this repository for? ###

* Authentication mechanism for the next generation which eliminates the use of a fixed passcode/password in favor of a machine learning model which uses various signals to generate a unique fingerprint as the user types in randomly generated numeric codes. 
* Version 1.0 - Created by Anirudh Ramanathan (anirudh4444@tamu.edu) and Ashish Kumar (ashishkumar@gatech.edu) at GTHack 2015.

### How do I get set up? ###

* Can be directly imported into Android Studio. 
* Dependencies: Included
* Deployment instructions: Android device with Android 5.x or higher recommended.
