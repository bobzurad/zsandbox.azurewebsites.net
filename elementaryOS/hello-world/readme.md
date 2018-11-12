Native Application for [elementaryOS](https://elementary.io)

built from: https://elementary.io/docs/code/getting-started#getting-started

## dev prerequisites
Must be running elementaryOS. Install the sdk.
```
sudo apt install elementary-sdk
```

## building & running
build and run in the src folder.

to build:
```
valac --pkg gtk+-3.0 Application.vala
```

to run:
```
./Application
```