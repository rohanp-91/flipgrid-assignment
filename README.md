### Description

This project is a demo for the Flipgrid coding assignment. It contains a simple Android application that consists of two screens - Register and Confirmation screen. The user is shown the registration screen where they enter their data to register to the app - email and password are required fields. Once the user submits the data using the submit button, the user will be shown the confirmation screen that will display the user's registered data.

The app uses Android material design for the UI elements. Below is a brief description of the various classes and components:

1. Global - This contains all the global classes that is used throughout the application's lifecycle by different activities. 
    * AppContext - This is an extension of the Application class. The benefits of having a custom App Context is that it allows us to customize and control the application's lifecycle - we can perform custom actions before the launch of different activities and also maintain a global context of user and app specific information. 
    * AppLogger - This is a global class for logging app and usage information. The AppContext contains an instance of the AppLogger. 
    * PreferenceWrapper - Wrapper class for SharedPreferences class of the application used to store critical app/user info in key-value pairs.
    * IAppLogger - An interface for the AppLogger - the interface is created just to enable future developers to create multiple logging classes that supports logging to different endpoints.
    * DataKey - An enum class for storing 'keys' for SharedPrefernce's key-value pairs or other key value pairs used in the app.

2. Network - This contains a very basic representation of how the application's network infrastructure might look like.
    * IRequestService - An interface for the network service that contains signature for get and post methods.
    * IResponseCallback - An onterface for the network response handler that contains signature for onSuccess and onFailure.
    * BaseRequestService - A root implemenetation of the request service class. Can be extended by each component of the class according to their needs.
    * RegisterRequestService - This is an extension of the BaseRequestService class to align with the registration network requests.
    * RegistrationRepository - A class that handles the registration requests making use of the RegisterRequestService.

3. Activities - This contains the class definitions of the various activities of the application.

4. Fragments - This contains the fragment definitions of the application that is used by the activities.