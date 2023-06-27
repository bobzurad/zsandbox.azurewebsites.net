# Ecomapp Mobile App

This is a mobile app created with `npx react-native init`

### API

This project interacts with data from the API at https://github.com/bobzurad/zsandbox.azurewebsites.net/tree/master/java/ecomapp
 - Note: If you have this API running on your localhost and you want to access it from the phone or emulator, run `adb reverse tcp:8080 tcp:8080` before starting the app. For more, see [here](https://dev.to/tusharsadhwani/connecting-android-apps-to-localhost-simplified-57lm)

### Libraries

Some important libraries/packages that this app uses includes:

- Navigation/Routing: [React Navigation](https://reactnavigation.org/docs/getting-started/)
- HTTP Client: [Axios](https://www.npmjs.com/package/axios)
- UI Framework: [React Native Paper](https://callstack.github.io/react-native-paper/index.html)


## Prerequisites and Dev Setup

This project requires NodeJS 14 or later. To setup your dev environment, follow the official [React Native Guide](https://reactnative.dev/docs/environment-setup) and select the `React Native CLI QuickStart`. This project **DOES NOT** use Expo! Stop at "Creating a new application", because this application is already created :)

After you cloned this repo, run `npm install`

If you're on macOS, also run `cd ios` and `pod install`

### Additional Notes

If using VSCode, make sure the following extensions are installed:

- React Native Tools (msjsdiag.vscode-react-native)
- ESLint (dbaeumer.vscode-eslint)
- Prettier (esbenp.prettier-vscode)


## Running the project

First, start Metro by running `npm start`

You will be prompted to select/start the Android or iOS simulator. Or you can run `npm android` or `npm ios` to start the Android or iOS simulator. 

## Android APK file

The Android APK file is loacated in `android/app/build/outputs/apk/debug/app-debug.apk` which you can copy to an Android device and install it.