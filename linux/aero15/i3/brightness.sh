#!/bin/bash

max_brightness=$(head -n 1 /sys/class/backlight/intel_backlight/max_brightness)
echo $max_brightness
