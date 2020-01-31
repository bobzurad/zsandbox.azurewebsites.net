these files last used on a 2012 MacBook Air running Arch Linux with i3

## prereqs

```bash
sudo pacman -S pasystray paprefs pavucontrol blueman i3lock i3status
```

### backlight.rules

place this file in /etc/udev/rules.d/

then run:
```bash
usermod -aG video <user>
```

place brightness.sh in ~/.config and make sure it's executable

### i3lock.service
https://hund0b1.gitlab.io/2019/01/08/using-i3lock-with-systemd-suspend.html

place i3lock.service and i3lockscreen.sh in /etc/systemd/system

then enable this service with:
```bash
systemctl enable i3lock.service
```
