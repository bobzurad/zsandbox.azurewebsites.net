these files last used on a Gigabyte Aero 15 laptop running PopOS 19.10 with lightDM

## prereqs

```bash
sudo apt install pasystray paprefs pavucontrol pavumeter blueman i3lock i3status
```

### backlight.rules

place this file in /etc/udev/rules.d/

then run:
```bash
usermod -aG video <user>
```

### i3lock.service

enable this service with:
```bash
systemctl enable i3lock.service
```

