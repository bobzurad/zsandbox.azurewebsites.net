# Docker-OSX

These instructions assume we're using https://github.com/sickcodes/Docker-OSX
But if that gets taken down, here's a few alternatives:
 * https://git.polyfish0.de/mika/Docker-OSX   (clone of sickcodes)
 * https://github.com/dockur/macos  (a separate implementation, never tried it)

 These instructions were last tested with a Linux host running Arch with a Wayland windowing system.
 
## Prerequisites
```bash
# ARCH
sudo pacman -S qemu libvirt dnsmasq virt-manager bridge-utils flex bison iptables-nft edk2-ovmf

# UBUNTU DEBIAN
sudo apt install qemu qemu-kvm libvirt-clients libvirt-daemon-system bridge-utils virt-manager libguestfs-tools

# CENTOS RHEL FEDORA
sudo yum install libvDPirt qemu-kvm
```

Then enable libvirt and load the KVM kernel module:l
```bash
sudo systemctl enable --now libvirtd
sudo systemctl enable --now virtlogd

echo 1 | sudo tee /sys/module/kvm/parameters/ignore_msrs

sudo modprobe kvm
```

If running Wayland on the host:
```bash
sudo pacman -S xorg-xhost
xhost +local:docker
```

## Create the Base Image

```bash
git clone https://github.com/sickcodes/Docker-OSX.git
cd Docker-OSX
```
Open the file Dockerfile
Search for the line that says

```Dockerfile
RUN make \
  && qemu-img convert BaseSystem.dmg -O qcow2 -p -c BaseSystem.img \
  && rm ./BaseSystem.dmg
```
Insert following above the found line:
```Dockerfile
RUN sed -i -e 's/os.get_terminal_size().columns/80/g' /home/arch/OSX-KVM/./fetch-macOS-v2.py
```
Then run this to build the image:
```bash
docker build -t sickcodes/docker-osx:sonoma --build-arg SHORTNAME=sonoma .
```

If the  base image fails to build, you can try pulling one of these images:
 * seraphix/docker-osx:sonoma
 * registry.cn-hangzhou.aliyuncs.com/shrimp-images/docker-osx:ventura

## Start the Base Image and install macOS

```bash
docker run -it \
    --device /dev/kvm \
    -p 50922:10022 \
    -v /tmp/.X11-unix:/tmp/.X11-unix \
    -e "DISPLAY=${DISPLAY:-:0.0}" \
    -e TERMS_OF_USE=i_agree \
    -e GENERATE_UNIQUE=true \
    -e CPU='Haswell-noTSX' \
    -e CPUID_FLAGS='kvm=on,vendor=GenuineIntel,+invtsc,vmware-cpuid-freq=on' \
    -e MASTER_PLIST_URL='https://raw.githubusercontent.com/sickcodes/osx-serial-generator/master/config-custom.plist' \
    sickcodes/docker-osx:sonoma
```

Select macOS Base System

Click Disk Utility

Erase the BIGGEST disk (mine was 275GB), DO NOT MODIFY THE SMALLER DISKS. -- if you can't click erase, you may need to reduce the disk size by 1kb

Create a partition using the unused space to house the OS and your files if you want to limit the capacity. (For Xcode 12 partition at least 60gb.)

Quit Disk Utility and click Reinstall macOS

The macOS installer will reboot a few times. When it does, select "macOS Installer" on boot. Then on subsequent reboots, select macOS.

## Extract the Base Image
Here we will extract the Base Image to be used as a persisted volume.

Use this command to find the base image.
```bash
sudo find /var/lib/docker -size +10G | grep mac_hdd_ng.img
```

Copy mac_hdd_ng.img to a folder in user space. This copy will be used for persistance when using macOS.

Then set permissions on the copied file:
```bash
sudo chown bob:bob mac_hdd_ng.img
chmod 666 mac_hdd_ng.img
```

## Create a Naked Image
This image will be used when mounting the persisted volume.

Open `Dockerfile.naked` and change
```Dockerfile
FROM sickcodes/docker-osx:latest
```
to
```Dockerfile
FROM sickcodes/docker-osx:sonoma
```

Then build the image:
```bash
docker build -t sickcodes/docker-osx:naked -f Dockerfile.naked .
```

## Create and start a macOS container
This will create and start a docker container, using the naked image with the base image mounted as a persisted volume. We name the container `macvm`

```bash
docker run -it \
    --device /dev/kvm \
    --name macvm \
    --network host \
    -p 50922:10022 \
    -v "${PWD}/mac_hdd_ng.img:/image" \
    -v /tmp/.X11-unix:/tmp/.X11-unix \
    -e CPU='Haswell-noTSX' \
    -e CPUID_FLAGS='kvm=on,vendor=GenuineIntel,+invtsc,vmware-cpuid-freq=on' \
    -e EXTRA='-smp 16,cores=8,threads=2' \
    -e RAM=24 \
    -e "DISPLAY=${DISPLAY:-:0.0}" \
    -e "NOPICKER=true" \
    sickcodes/docker-osx:naked
```

When the container is shut down and no longer running, it can be started again by running:

```bash
docker start -ai macvm
```
