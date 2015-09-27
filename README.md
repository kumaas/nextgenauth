## Keystroke-Biometric Authenticator for Android ##

### What is this repository for? ###

* An Android application showcasing an authentication mechanism for the next generation which eliminates the use of a fixed passcode/password in favor of a machine learning model which uses various signals to generate a unique fingerprint as the user types in randomly generated numeric codes. 
* Version 1.0 - Created by Anirudh Ramanathan (anirudh4444@tamu.edu) and Ashish Kumar (ashishkumar@gatech.edu) at GTHack 2015.

### Concept ###

* Numeric code based authentication schemes can easily lead to stolen credentials.
* Two-factor authentication is cumbersome.
* We have come up with a model which with further improvements, may change authentication works in future.

Early on, we learned from our experiments that if we plotted the screen coordinates at which users touched various numbers on a numeric keyboard on a touchscreen, one would obtain a Gaussian distribution, such as below. The X coordinate variation is shown for various keys on a numeric on-screen keyboard for a single user. 

![x-axis.png](https://bitbucket.org/repo/B6beke/images/3900933863-x-axis.png)

Our idea is to use the following signals to build the unique fingerprint of a user, while asking him/her to train a Gaussian mixture model and then using that model to authenticate the user after that. A major merit of this method is that one is not constrained to using a single passcode/password to authenticate the user. This additionally also gives an extra layer of security over passcodes, because emulating one's use of an electronic device is rather challenging.

We looked at the following signals to generate a unique fingerprint and model for a particular user:
* Typing heatmap (spatial coordinates of points that the user struck)
* Pressure
* Size of contact with the touchscreen
* Duration of contact with the touchscreen 

## Implementation ##

Our application is implemented using Android because it provided to us, APIs that allowed us to query the aforementioned signals, and because we felt that the variety in Android devices would let us test the efficacy of our handpicked signals for the purpose of authentication.

The main screen of the training application is shown below:
![training_application.png](https://bitbucket.org/repo/B6beke/images/1869717867-training_application.png)

This lets us both train the model and use the model we have, to compare against user-provided random codes to check for various similarities in the typing pattern and authenticate them. The toggle-button at the bottom lets one switch between training and authentication modes. It is generally recommended that one train the model with least a hundred samples before attempting to run input against the model. The numeric code in the textbox to the top-right is provided as a guideline. It is a set of randomly selected numbers which the user may/may not choose to use. We recommend its use because it produces a well-distributed pattern that is distributed and is likely to reflect one's natural typing pattern. 

After each authentication attempt, the application queries the user and records whether it made the right decision. We would like to note that at this point, upon a correct prediction, the application would provide feedback and update the training set. However, we chose not to implement the feedback loop in order to retain the ability to reason about our model in an easier fashion due to time constraints.

The statistics we obtained are represented below. This screen can be obtained by clicking on "metrics" from the main application screen.

![metrics.png](https://bitbucket.org/repo/B6beke/images/404403605-metrics.png)
## Keystroke-Biometric Authenticator for Android ##

### What is this repository for? ###

* An Android application showcasing an authentication mechanism for the next generation which eliminates the use of a fixed passcode/password in favor of a machine learning model which uses various signals to generate a unique fingerprint as the user types in randomly generated numeric codes. 
* Version 1.0 - Created by Anirudh Ramanathan (anirudh4444@tamu.edu) and Ashish Kumar (ashishkumar@gatech.edu) at GTHack 2015.

### Concept ###

* Numeric code based authentication schemes can easily lead to stolen credentials.
* Two-factor authentication is cumbersome.
* We have come up with a model which with further improvements, may change authentication works in future.

Early on, we learned from our experiments that if we plotted the screen coordinates at which users touched various numbers on a numeric keyboard on a touchscreen, one would obtain a Gaussian distribution, such as below. The X coordinate variation is shown for various keys on a numeric on-screen keyboard for a single user. 

![x-axis.png](https://bitbucket.org/repo/B6beke/images/3900933863-x-axis.png)

Our idea is to use the following signals to build the unique fingerprint of a user, while asking him/her to train a Gaussian mixture model and then using that model to authenticate the user after that. A major merit of this method is that one is not constrained to using a single passcode/password to authenticate the user. This additionally also gives an extra layer of security over passcodes, because emulating one's use of an electronic device is rather challenging.

We looked at the following signals to generate a unique fingerprint and model for a particular user:
* Typing heatmap (spatial coordinates of points that the user struck)
* Pressure
* Size of contact with the touchscreen
* Duration of contact with the touchscreen 

## Implementation ##

Our application is implemented using Android because it provided to us, APIs that allowed us to query the aforementioned signals, and because we felt that the variety in Android devices would let us test the efficacy of our handpicked signals for the purpose of authentication.

The main screen of the training application is shown below:

![training_application.png](https://bitbucket.org/repo/B6beke/images/1869717867-training_application.png)

This lets us both train the model and use the model we have, to compare against user-provided random codes to check for various similarities in the typing pattern and authenticate them. The toggle-button at the bottom lets one switch between training and authentication modes. It is generally recommended that one train the model with least a hundred samples before attempting to run input against the model. The numeric code in the textbox to the top-right is provided as a guideline. It is a set of randomly selected numbers which the user may/may not choose to use. We recommend its use because it produces a well-distributed pattern that is distributed and is likely to reflect one's natural typing pattern. 

After each authentication attempt, the application queries the user and records whether it made the right decision. We would like to note that at this point, upon a correct prediction, the application would provide feedback and update the training set. However, we chose not to implement the feedback loop in order to retain the ability to reason about our model in an easier fashion due to time constraints.

The statistics we obtained are represented below. This screen can be obtained by clicking on "metrics" from the main application screen.

### How do I get set up? ###

* Can be directly imported into Android Studio. 
* Dependencies: Included
* Deployment instructions: Android device with Android 5.x or higher recommended.