Native Application for [elementaryOS](https://elementary.io)

built from: https://elementary.io/docs/code/getting-started#getting-started

## dev prerequisites
Must be running elementaryOS. Install the sdk.
```
sudo apt install elementary-sdk
```

## build
This app uses the Meson build system. Configure Meson with:
```
meson build --prefix=/usr
```

## install
```
cd build
ninja
sudo ninja install
```
You will now see the app in the Applications Menu, named Hello Again.
