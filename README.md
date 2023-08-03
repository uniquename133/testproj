Preconditions:
To run tests you need corresponding services installed on your pc, so install everything before using start this project
guide - https://www.guru99.com/introduction-to-appium.html

How to start tests:

1. Rename your apk file to 'app.apk' and place it into /apps folder
2. Start emulator or connect real device to computer
3. Set your device name as 'android.device-name' property in /src/test/resources/config.properties
4. Run 'mvn clean install' from the project's root directory

Report can be found in /target/cucumber-report/ folder