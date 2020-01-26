these files last used on a 2012 MacBook Air running PopOS 19.10 with Gnome Display Manager (gdm)

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

### compton.conf

taken from here: https://www.reddit.com/r/linuxmasterrace/comments/3hp37x/for_anyone_having_lag_with_compton_this_might/

### i3lock.service

enable this service with:
```bash
systemctl enable i3lock.service
```
