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
UPDATE: remove this and install LightDM instead

https://hund0b1.gitlab.io/2019/01/08/using-i3lock-with-systemd-suspend.html

place i3lock.service and i3lockscreen.sh in /etc/systemd/system

then enable this service with:
```bash
systemctl enable i3lock.service
```

### trackpad enabled while typing

Add this to each section in `/usr/share/X11/xorg.conf.d/40-libinput.conf` below `Driver "libinput"`

```bash
  Option "DisableWhileTyping" "false"
```
