# Building the Web Scraper APK

This guide explains how to build the Web Scraper Android APK from source.

## Prerequisites

- **Java JDK 11+** (for Gradle)
- **Android SDK** (API level 34)
- **Build Tools** 34.0.0+
- **Gradle 9.4.0+**
- **Python 3.8+** (for Buildozer method)

## Method 1: Using Gradle (Recommended for Android Developers)

### Setup

1. **Install Android SDK (if not already installed)**
   ```bash
   # Using sdkman (recommended)
   curl -s "https://get.sdkman.io" | bash
   sdk install java 25.0.2-ms
   sdk install gradle 9.4.0
   
   # Download Android SDK
   mkdir -p ~/Android/Sdk
   ```

2. **Set Environment Variables**
   ```bash
   export ANDROID_SDK_ROOT=$HOME/Android/Sdk
   export ANDROID_HOME=$ANDROID_SDK_ROOT
   export PATH=$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$PATH
   export PATH=$ANDROID_SDK_ROOT/platform-tools:$PATH
   ```

3. **Accept Licenses**
   ```bash
   sdkmanager --licenses
   ```

### Build Steps

1. **Navigate to android_build directory**
   ```bash
   cd android_build
   ```

2. **Build Debug APK**
   ```bash
   gradle assembleDebug
   ```
   Output: `app/build/outputs/apk/debug/app-debug.apk`

3. **Build Release APK** (unsigned)
   ```bash
   gradle assembleRelease
   ```
   Output: `app/build/outputs/apk/release/app-release-unsigned.apk`

4. **Sign the Release APK**
   ```bash
   # Create a keystore (first time only)
   keytool -genkey -v -keystore release.keystore -alias webscraper \
     -keyalg RSA -keysize 2048 -validity 10000
   
   # Sign the APK
   jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 \
     -keystore release.keystore \
     app/build/outputs/apk/release/app-release-unsigned.apk webscraper
   
   # Zipalign (optimize)
   zipalign -v 4 app/build/outputs/apk/release/app-release-unsigned.apk \
     app/build/outputs/apk/release/WebScraper-3.0.0.apk
   ```

### Troubleshooting

**Problem**: Build fails with "SDK not found"
```bash
# Solution: Set ANDROID_SDK_ROOT
export ANDROID_SDK_ROOT=/path/to/android/sdk
```

**Problem**: Gradle command not found
```bash
# Solution: Install Gradle
sdk install gradle 9.4.0
# or
brew install gradle  # macOS
```

**Problem**: Java version incompatibility
```bash
# Solution: Use Java 11 or newer
java -version
# Install if needed:
sdk install java 25.0.2-ms
```

---

## Method 2: Using Buildozer (Python-based)

Buildozer automates the entire APK building process for Python apps.

### Setup

1. **Install Buildozer**
   ```bash
   pip install buildozer
   ```

2. **Install Dependencies**
   ```bash
   # Ubuntu/Debian
   sudo apt install python3-dev buildozer cython virtualenv

   # macOS
   brew install buildozer
   
   # Or use buildozer itself to download dependencies
   buildozer android api_level=34 ndk_version=25b
   ```

### Build Steps

1. **Navigate to android_build directory**
   ```bash
   cd android_build
   ```

2. **Initialize Buildozer** (creates buildozer.spec)
   ```bash
   buildozer init
   ```
   (Already created in this project)

3. **Build APK**
   ```bash
   # Debug build
   buildozer android debug
   
   # Release build
   buildozer android release
   ```

   Output: `bin/WebScraper-3.0.0-debug.apk` or `.../release.apk`

### Buildozer Configuration

The `buildozer.spec` file contains:
- App name: Web Scraper
- Package name: webscraper
- Version: 3.0.0
- Minimum API: 24
- Target API: 34
- Required permissions: INTERNET, STORAGE

### Troubleshooting

**Problem**: NDK download fails
```bash
# Solution: Manually download NDK
buildozer android ndk_download_dir=/path/to/ndk
```

**Problem**: Build taking too long
```bash
# Solution: Increase memory for Gradle
export GRADLE_OPTS="-Xmx4096m -Xms512m"
```

---

## Method 3: Using Docker (Isolated Environment)

For complete isolation and reproducibility:

```dockerfile
FROM ubuntu:24.04

RUN apt-get update && apt-get install -y \
    python3 python3-dev python3-pip \
    openjdk-11-jdk \
    git \
    wget \
    unzip \
    buildozer \
    cython \
    virtualenv

# Install Android SDK
RUN mkdir -p /opt/android-sdk && cd /opt/android-sdk && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-10406996_latest.zip && \
    unzip -q commandlinetools-linux-10406996_latest.zip && \
    yes | cmdline-tools/bin/sdkmanager --sdk_root=. --licenses

# Install NDK and build tools
RUN cmdline-tools/bin/sdkmanager --sdk_root=/opt/android-sdk \
    "platforms;android-34" \
    "build-tools;34.0.0" \
    "ndk;25.2.9519653"

ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV ANDROID_HOME=/opt/android-sdk

# Build
WORKDIR /app
COPY . .
RUN cd android_build && buildozer android release
```

Build with:
```bash
docker build -t webscraper-builder .
docker run -v $(pwd)/android_build/bin:/app/android_build/bin webscraper-builder
```

---

## Installation on Device

After building, install on your Android device:

```bash
# Using adb (Android Debug Bridge)
adb devices
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Or manually
# Copy the APK to your device via USB or transfer service
# Then tap to install
```

---

## File Locations

After successful build:

- **Debug APK**: `android_build/app/build/outputs/apk/debug/app-debug.apk`
- **Release APK (unsigned)**: `android_build/app/build/outputs/apk/release/app-release-unsigned.apk`
- **Release APK (signed)**: `android_build/WebScraper-3.0.0.apk`
- **Buildozer APK**: `android_build/bin/WebScraper-3.0.0-debug.apk`

---

## Continuous Integration

For automated builds with GitHub Actions:

```yaml
name: Build APK

on: [push]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '11'
      - name: Build APK
        run: |
          cd android_build
          gradle assembleDebug
      - name: Upload APK
        uses: actions/upload-artifact@v3
        with:
          name: app-debug.apk
          path: android_build/app/build/outputs/apk/debug/
```

---

## APK File Info

- **Name**: Web Scraper
- **Package**: com.webscraper.app
- **Min API**: Android 7.0 (API 24)
- **Target API**: Android 14 (API 34)
- **Architecture**: arm64-v8a
- **Size**: ~10-15 MB (depends on included libraries)

---

## Support

For issues:
1. Check the troubleshooting section above
2. Verify Android SDK is properly installed: `adb --version`
3. Check Java version: `java -version` (should be 11+)
4. Review Gradle logs: `gradle assembleDebug --stacktrace`

---

**Version**: 3.0.0  
**Last Updated**: 2026-06-29
