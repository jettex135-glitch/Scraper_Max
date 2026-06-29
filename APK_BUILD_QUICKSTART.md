# 🚀 Web Scraper APK - Quick Build Guide

## For End Users: Pre-Built APK Coming Soon

A pre-built APK of Web Scraper v3.0.0 with the complete tutorial system will be available in the GitHub Releases section once the environment build process is completed.

## For Developers: Build from Source

### Quick Build (Recommended for Most Users)

The easiest way to build the APK is on your local machine where you control the environment.

#### Prerequisites
```bash
# 1. Install Java (11 or higher)
java -version  # Should show Java 11+

# 2. Install Android SDK
# Option A: Using Android Studio
# - Download from https://developer.android.com/studio
# - Installation creates ~/Android/Sdk automatically

# Option B: Using command-line tools
mkdir -p ~/Android/Sdk
cd ~/Android/Sdk
# Download from https://developer.android.com/studio#command-tools
# Extract and set ANDROID_SDK_ROOT

# 3. Set environment variables
export ANDROID_SDK_ROOT=$HOME/Android/Sdk
export ANDROID_HOME=$ANDROID_SDK_ROOT
export PATH=$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$PATH
export PATH=$ANDROID_SDK_ROOT/platform-tools:$PATH

# 4. Accept Android licenses
sdkmanager --licenses
```

### Build Steps

#### Step 1: Clone the Repository
```bash
git clone https://github.com/jettex135-glitch/Scraper_Max.git
cd Scraper_Max/android_build
```

#### Step 2: Build Debug APK (Easiest)
```bash
# Use Gradle 8.6 (best compatibility with AGP 8.1.0)
gradle assem bleDebug

# Or if you have gradlew in the project:
chmod +x gradlew
./gradlew assembleDebug
```

Output: `app/build/outputs/apk/debug/app-debug.apk`

#### Step 3: Build Release APK (Optional)
```bash
gradle assembleRelease

# This creates: app/build/outputs/apk/release/app-release-unsigned.apk
```

#### Step 4: Sign the Release APK (Optional)
```bash
# Create keystore (one time only)
keytool -genkey -v -keystore my-release-key.keystore \
  -keyalg RSA -keysize 2048 -validity 10000 -alias webscraper

# Sign the APK
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 \
  -keystore my-release-key.keystore \
  app/build/outputs/apk/release/app-release-unsigned.apk webscraper

# Optimize (zipalign)
zipalign -v 4 app/build/outputs/apk/release/app-release-unsigned.apk \
  WebScraper-3.0.0-release.apk
```

### Install on Device

```bash
# Using ADB (Android Debug Bridge)
adb devices                              # List connected devices
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Or manually:
# 1. Copy APK file to phone via USB
# 2. Use file manager to open APK file
# 3. Tap to install
```

### Troubleshooting Build Issues

**Issue: "Android SDK not found"**
```bash
# Solution: Set ANDROID_SDK_ROOT
export ANDROID_SDK_ROOT=$HOME/Android/Sdk
export ANDROID_HOME=$ANDROID_SDK_ROOT
```

**Issue: "Gradle not found"**
```bash
# Solution: Install Gradle
brew install gradle         # macOS
apt-get install gradle      # Ubuntu/Debian
# Or use sdkman:
sdk install gradle 8.6
```

**Issue: "Build fails with Java error"**
```bash
# Solution: Use compatible Java version
java -version              # Should be 11 or higher
sdk install java 21.0.0    # If needed
```

**Issue: gradle.properties/settings.gradle errors**
```bash
# Solution: Clean and retry
rm -rf .gradle build
gradle clean assembleDebug
```

## Project Structure

```
android_build/
├── app/
│   ├── build.gradle           # App build configuration
│   ├── src/main/
│   │   ├── AndroidManifest.xml          # App permissions & activities
│   │   ├── java/com/webscraper/
│   │   │   ├── MainActivity.java        # Main UI with tutorial button
│   │   │   └── TutorialActivity.java    # 10-step interactive guide
│   │   ├── res/
│   │   │   ├── values/strings.xml       # App strings & help text
│   │   │   ├── mipmap-*/ic_launcher.png # App icons (5 sizes)
│   │   │   ├── values/colors.xml        # Theme colors
│   │   │   └── values/styles.xml        # App styles
│   │   └── assets/
│   │       ├── Duke2_Enhanced.py        # Core scraper script
│   │       └── TUTORIAL.md              # Extended guide
│   └── build/outputs/apk/
│       └── debug/app-debug.apk          # Built APK file
├── build.gradle                # Project-level Gradle config
├── settings.gradle              # Gradle project settings
├── buildozer.spec               # Buildozer config (alternative)
└── gradle.properties            # Gradle properties

```

## What's Included in the APK

✅ **Android GUI (v3.0.0)**
- Main app interface with button menu
- Material Design dark theme
- Responsive layout for all screen sizes

✅ **10-Step Interactive Tutorial**
- Welcome & Overview
- Android Installation
- Running the Scraper
- Configuration Options
- Media Types Explanation
- Gallery Viewer Guide
- Cloudflare Bypass Engines
- Proxy Configuration
- Tips & Tricks
- Troubleshooting

✅ **In-App Help Buttons**
- Proxy Settings information
- Cloudflare Bypass engines
- Download folder access
- Direct Termux launcher

✅ **App Resources**
- Spider icon (5 Android sizes)
- Theme colors (red/pink accent)
- App strings and help text

✅ **Integrated Scripts**
- Duke2_Enhanced.py (core scraper)
- TUTORIAL.md (documentation)

## Gradle Compatibility Matrix

| Gradle | AGP   | Java  | Status |
|--------|-------|-------|--------|
| 9.4.0  | 8.1.0 | 25.0+ | ⚠️ Issues in some environments |
| 8.6    | 8.1.0 | 17+   | ✅ Recommended |
| 8.5    | 8.1.0 | 17+   | ✅ Works |
| 8.0    | 8.1.0 | 11+   | ✅ Works |

**Best Practice**: Use Gradle 8.6 with Java 17+ for smooth builds.

## File Sizes

- APK (debug, unoptimized): ~15-20 MB
- APK (release, optimized): ~12-15 MB
- Installation size on device: ~25-30 MB (with dependencies)

## Next Steps After Building

1. **Install APK on Android device**
   ```bash
   adb install -r WebScraper-3.0.0.apk
   ```

2. **Launch the app**
   - Tap the Web Scraper icon on home screen
   - You'll see the main menu with 6 options

3. **First-time setup**
   - Tap "📖 Full Tutorial" for complete walkthrough
   - Follow steps 1-3 for Android/Termux installation
   - Then come back to the app to run the scraper

## Support

For detailed build troubleshooting, see [BUILD_APK.md](../BUILD_APK.md)

For usage questions, see [README.md](../README.md) or tap the tutorial button in the app.

---

**Version**: 3.0.0  
**Last Updated**: 2026-06-29

