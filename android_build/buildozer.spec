[app]
# Application title and package
title = Web Scraper
package.name = webscraper
package.domain = com.duke2

# Source code location
source.dir = .
source.include_exts = py,png,jpg,kv,atlas,ttf,java,xml,gradle

# Version info
version = 3.0.0

# Python requirements
python_version = 3.11
requirements = python3,kivy,requests,beautifulsoup4,pillow,curl-cffi,cloudscraper

# Orientation and resolution
orientation = portrait
fullscreen = 1
resizable = 1

# Permissions
android.permissions = INTERNET,ACCESS_NETWORK_STATE,WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE,REQUEST_INSTALL_PACKAGES

# Target Android version
android.api = 34
android.minapi = 24
android.ndk = 25b

# App icon
android.icon.filename = %(source.dir)s/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png

# Architecture
android.archs = arm64-v8a

[buildozer]
# Output directory
bin_dir = ./bin
log_level = 2
warn_on_root = 1

