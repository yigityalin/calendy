Group: G2K

Title: Calendy: Utility Calendar

Description:
Calendy is a multipurpose calendar that allows its users to create and manage their timetables. 
At its core, calendy is composed of an annual calendar where users can add or organize various
tasks with particular properties. Users can be added to calendars, and new calendars can be
created and shared between users.

Status:
Mostly completed but subscribing to other people's calendars through search is left.

Contributions:

Cem Gülümser 22003430 - Implemented Welcome Page (Start Activity), Sign Up and Log In interfaces 
and their activities. Set navigation from welcome page to login or signup page.

Mehmet Kağan İlbak 22002379 - Implemented navigation, floating action menu and its activities;
implemented Date/TimePickerButton, User, Task (and its subclasses), Calendar classes, implemented the search and its ui,
Firebase integration, settings menu, edit profile menu, view each calendar menu, fixed various activity UIs.

Emir Kurban 21902653 -

Yiğit Yalın 22002178 - Implemented calendar UI for month and day views in home fragment; implemented UI for Calendars fragment; implemented click listeners for home and calendars fragment.

Yarkın Ergin 21902462 - Added preferences for settings ui.

How to run:
1- Open the project with Android Stuido
2- Create a new Android Virtual Device that has Google Play (must) and is API 29 (Android 10).
3- To create AVD, Navigate to Tools -> AVD Manager
4- Preferably, run the app on Pixel 3a API 29 (With Google Play Store).
5- You may experience crashes (e.g. NullPointerException) when you launch the app and quickly navigate between menus,
that's because sometimes fetching the data from Firebase takes too long. But we rarely experienced such crashes.
