# Ecomapp Mobile App

This is a mobile app created with `npx react-native init`

This project interacts with data from the API at https://github.com/bobzurad/zsandbox.azurewebsites.net/tree/master/java/ecomapp

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