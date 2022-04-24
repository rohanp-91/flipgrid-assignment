### Description

This project is a demo for the Flipgrid coding assignment. It contains a simple Android application that consists of two screens - Register and Confirmation screen. The user is shown the registration screen where they enter their data to register to the app - email and password are required fields. Once the user submits the data using the submit button, the user will be shown the confirmation screen that will display the user's registered data.

The app uses Android material design for the UI elements. Below is a brief description of the various classes and components:

# Global - This contain all the global classes that is used throughout the application's lifecycle by different activities. 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;AppContext - This is an extension of the Application class. The benefits of having a custom App Context is that it allows us to &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;customize and control the application's lifecycle - we can perform custom actions before the launch of different activities and &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;also maintain a global context of user and app specific information. 