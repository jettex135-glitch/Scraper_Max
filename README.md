# 🕷️ Web Scraper - Advanced Media Download Tool

A powerful, open-source media scraper for Android (via Termux) with built-in **Cloudflare bypass**, **interactive gallery viewer**, **comprehensive tutorials**, and **proxy support**.

## ✨ Features

### 🛡️ Advanced Bypass Engines
- **curl_cffi** - TLS fingerprint impersonation (JA3/JA4 bypass) - Recommended
- **cloudscraper** - JavaScript challenge solver
- **Standard requests** - Fallback with basic HTTP
- Automatic engine selection and fallback

### 📥 Media Download Capabilities
- **Images**: jpg, png, gif, webp, svg, bmp, tiff, avif
- **Videos**: mp4, webm, mkv, flv, avi, mov, 3gp, ogv
- **Audio**: mp3, ogg, wav, flac, aac, m4a, opus, wma
- **Documents**: pdf, epub, docx, txt, doc, rtf, odt
- **Archives**: zip, rar, 7z, tar, gz, bz2, xz
- **eBooks**: mobi, azw3, azw, cbz, cbr

### 🎨 User Interface
- **Android GUI** with intuitive button-based navigation
- **Full Tutorial System** with 10 comprehensive guides
- **In-App Help** for all major features
- **Interactive Gallery Viewer** for downloaded media
- Responsive dark theme design

### ⚙️ Configuration Options
- Customizable crawl depth and page limits
- Minimum file size filtering
- Per-type download limits
- Smart retry with exponential backoff
- Header rotation and TLS fingerprint impersonation

### 🌐 Advanced Features
- **Lazy-load Media Extraction** - Gets hidden/JS-rendered content
- **Locked Content Detection** - Identifies premium/subscriber-only files
- **Proxy Support** - HTTP, SOCKS5, rotating proxies
- **Session Persistence** - Cookie saving for authenticated sites
- **Multi-threaded Downloads** - Up to 8 parallel connections
- **Smart Rate Limiting** - Automatic backoff on 429 responses

### 📊 Results Management
- Automatic HTML gallery generation
- JSON results export
- CSV results export
- ZIP archive of all media
- Metadata logging

## 📖 Built-In Tutorial System

The app includes a comprehensive 10-step tutorial covering:

1. **Welcome & Overview** - Features and capabilities introduction
2. **Android Installation** - Step-by-step setup instructions
3. **Running the Scraper** - Basic usage guide
4. **Configuration Options** - Parameter explanation
5. **Media Types** - Format support details
6. **Gallery Viewer** - Results navigation guide
7. **Cloudflare Bypass** - Bypass engine comparison
8. **Proxy Configuration** - Proxy setup guide
9. **Tips & Tricks** - Advanced usage patterns
10. **Troubleshooting** - Common issues and solutions

Access the tutorial via the **"📖 Full Tutorial"** button in the app.

## 🚀 Quick Start

### Prerequisites
- Android device with Termux
- Python 3.8+
- Storage permissions

### Installation

1. **Install Termux** (from F-Droid, NOT Google Play)
   ```bash
   https://f-droid.org/packages/com.termux/
   ```

2. **Update and Install Python**
   ```bash
   pkg update
   pkg install python -y
   termux-setup-storage
   ```

3. **Install Dependencies**
   ```bash
   pip install requests beautifulsoup4 curl-cffi cloudscraper Pillow
   ```

4. **Launch Web Scraper App**
   - Open the Web Scraper app
   - Tap "▶️ Start Scraper" to launch Termux
   - Or run directly: `python Duke2_Enhanced.py`

### Using the App

1. Open **Web Scraper** app on your Android device
2. Tap **"▶️ Start Scraper"** - opens Termux with the scraper
3. **Enter website URL** when prompted
4. **Select media types** to download (1-7)
5. **Configure options**:
   - Minimum file size
   - Crawl depth (how many link levels to follow)
   - Maximum pages
   - Same domain only? (y/n)
6. **Choose bypass engine** (default: auto)
7. **Set download limits** per media type
8. Wait for downloads to complete
9. View results in gallery: **"🖼️ Open Gallery"**

### File Locations

- **Downloads**: `/sdcard/Download/Duke2/`
- **Gallery**: `/sdcard/Download/Duke2/gallery.html`
- **Results**: `/sdcard/Download/Duke2/results.json` (metadata)

## 🎯 Using the Tutorial

New users should:

1. **First Launch**: Read the welcome screen in the tutorial
2. **Installation Issues**: Check "Android Installation" guide
3. **First Run**: Follow "Running the Scraper" step-by-step
4. **Questions**: Use in-app help buttons for feature explanations
5. **Problems**: Check "Troubleshooting" section

Navigate the tutorial with:
- **⬅️ Previous** - Go to previous step
- **Next ➜** - Go to next step
- **← Back** - Return to main app menu

## 🛡️ Cloudflare Bypass Explained

### Why is it needed?
Some websites use Cloudflare's anti-bot protection. This scraper can bypass it using:

- **TLS Fingerprinting (curl_cffi)** - Makes requests look like real Chrome browser
- **JavaScript Solving (cloudscraper)** - Solves Cloudflare challenges
- **Standard Requests** - Works on unprotected sites

### Auto Selection
Choose option "1 - Auto" and the scraper will use the best available engine for your target site.

## 🌐 Proxy Configuration

### When to Use
- Large-scale scraping to avoid IP bans
- Access geo-restricted content
- Rotate IPs for high-volume requests

### Configuration
When prompted, enter proxy URL:
- HTTP: `http://proxy.example.com:8080`
- SOCKS5: `socks5://user:pass@proxy.com:1080`
- Authenticated: `http://user:pass@proxy.com:8080`

Leave blank for no proxy.

## 📊 Understanding Results

After scraping, you'll get:

- **gallery.html** - Interactive viewer for all media
- **results.json** - Metadata in JSON format
- **results.csv** - Spreadsheet-compatible format
- **media.zip** - Archive of all files
- **Organized folders** - By media type

## ⚙️ Advanced Features

### Session Persistence
Cookies are automatically saved to `~/.duke2_cookies.json` for re-authentication on subsequent runs.

### Lazy-Load Detection
The scraper detects media loaded by JavaScript (data-src, data-lazy-src, etc.) and extracts them.

### Locked Content Patterns
Recognizes common patterns for premium/subscriber content:
- `/locked/`, `/premium/`, `/exclusive/`
- `/members/`, `/subscriber/`, `/paid/`, `/vip/`
- Custom download endpoints

### Smart Rate Limiting
- Detects 429 (Rate Limited) responses
- Implements exponential backoff (2^attempt seconds)
- Automatic retry up to 5 times
- Optional proxy rotation for faster recovery

## 🐛 Troubleshooting

### Issue: "No gallery found"
**Solution**: Run the scraper first to generate results

### Issue: 403 Forbidden errors
**Solution**: 
- Use curl_cffi bypass engine
- Add a proxy
- Check User-Agent rotation is enabled

### Issue: 429 Rate Limited
**Solution**:
- Reduce crawl depth
- Increase delays (built-in exponential backoff)
- Use proxy for IP rotation

### Issue: Storage permission denied
**Solution**: Run `termux-setup-storage` in Termux

### Issue: Python packages not found
**Solution**: 
```bash
pip install --upgrade requests beautifulsoup4 curl-cffi cloudscraper Pillow
```

### Issue: Slow downloads
**Solution**:
- Increase number of threads (default 8)
- Use SSD storage if available
- Reduce min file size limit

## 📦 Building the APK

### Option 1: Quick Install (Python script only)
```bash
cd android_build
bash install_termux.sh
```

### Option 2: Gradle Build (Full APK)
```bash
cd android_build
./gradlew assembleDebug
```

### Option 3: Buildozer Build
```bash
cd android_build
buildozer android debug
```

## 🔧 Configuration Files

### buildozer.spec
Main app configuration:
- App name, version, package name
- Required permissions
- Supported architectures
- Minimum API level

### AndroidManifest.xml
Android system configuration:
- Activities and services
- Required permissions (INTERNET, STORAGE)
- File provider configuration

### strings.xml
Localized strings and help text

## 📝 License

Advanced Media Scraper - Open Source

## 🙏 Credits

Built with Python using:
- **requests** - HTTP library
- **BeautifulSoup** - HTML parsing
- **curl_cffi** - TLS fingerprinting
- **cloudscraper** - Cloudflare bypass
- **Pillow** - Image processing

## ⚖️ Legal Notice

This tool is for educational and lawful purposes only. Users are responsible for:
- Respecting website terms of service
- Following copyright laws
- Complying with local regulations
- Obtaining necessary permissions

Do not use for unauthorized access or distribution of copyrighted material.

## 🤝 Support

For issues or questions:
1. Check the in-app tutorial
2. Review the troubleshooting guide
3. Check GitHub issues
4. Consult TUTORIAL.md for detailed documentation

---

**Version**: 3.0.0  
**Last Updated**: 2026-06-29  
**Platform**: Android (via Termux)
