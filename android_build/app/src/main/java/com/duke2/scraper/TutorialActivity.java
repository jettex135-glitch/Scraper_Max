package com.webscraper.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Intent;

public class TutorialActivity extends Activity {
    
    private int currentStep = 0;
    private String[] tutorialTitles;
    private String[] tutorialContent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Initialize tutorial content
        initializeTutorial();
        
        // Main layout
        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 30, 30, 30);
        layout.setBackgroundColor(0xFF0A0A0F);
        
        // Back button
        Button btnBack = new Button(this);
        btnBack.setText("← Back");
        btnBack.setBackgroundColor(0xFF533483);
        btnBack.setTextColor(0xFFFFFFFF);
        btnBack.setOnClickListener(v -> finish());
        LinearLayout.LayoutParams backParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        backParams.setMargins(0, 0, 0, 20);
        btnBack.setLayoutParams(backParams);
        layout.addView(btnBack);
        
        // Title
        TextView tvTitle = new TextView(this);
        tvTitle.setText("📖 User Guide & Tutorial");
        tvTitle.setTextSize(26);
        tvTitle.setTextColor(0xFFE94560);
        tvTitle.setPadding(0, 0, 0, 20);
        layout.addView(tvTitle);
        
        // Content display
        TextView tvContent = new TextView(this);
        tvContent.setId(1);
        tvContent.setTextSize(16);
        tvContent.setTextColor(0xFFE0E0E0);
        tvContent.setLineSpacing(1.6f, 1.0f);
        tvContent.setPadding(0, 0, 0, 30);
        layout.addView(tvContent);
        
        // Navigation buttons
        LinearLayout navLayout = new LinearLayout(this);
        navLayout.setOrientation(LinearLayout.HORIZONTAL);
        navLayout.setWeightSum(2);
        
        Button btnPrev = new Button(this);
        btnPrev.setText("⬅️ Previous");
        btnPrev.setBackgroundColor(0xFF533483);
        btnPrev.setTextColor(0xFFFFFFFF);
        LinearLayout.LayoutParams prevParams = new LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1.0f
        );
        prevParams.setMargins(0, 0, 10, 0);
        btnPrev.setLayoutParams(prevParams);
        btnPrev.setOnClickListener(v -> {
            if (currentStep > 0) {
                currentStep--;
                updateContent();
            }
        });
        navLayout.addView(btnPrev);
        
        Button btnNext = new Button(this);
        btnNext.setText("Next ➜");
        btnNext.setBackgroundColor(0xFF533483);
        btnNext.setTextColor(0xFFFFFFFF);
        LinearLayout.LayoutParams nextParams = new LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1.0f
        );
        nextParams.setMargins(10, 0, 0, 0);
        btnNext.setLayoutParams(nextParams);
        btnNext.setOnClickListener(v -> {
            if (currentStep < tutorialTitles.length - 1) {
                currentStep++;
                updateContent();
            }
        });
        navLayout.addView(btnNext);
        
        layout.addView(navLayout);
        
        scrollView.addView(layout);
        setContentView(scrollView);
        
        updateContent();
    }
    
    private void initializeTutorial() {
        tutorialTitles = new String[]{
            "Welcome to Web Scraper",
            "Installation on Android",
            "Running the Scraper",
            "Configuration Options",
            "Understanding Media Types",
            "Gallery Viewer",
            "Cloudflare Bypass",
            "Proxy Configuration",
            "Tips & Tricks",
            "Troubleshooting"
        };
        
        tutorialContent = new String[]{
            "Welcome to Web Scraper! 🕷️\n\n" +
            "Web Scraper is an advanced media downloading tool that can extract images, videos, audio, documents, and more from websites.\n\n" +
            "Key Features:\n" +
            "• Cloudflare bypass (TLS fingerprint impersonation)\n" +
            "• Interactive gallery viewer\n" +
            "• Multi-threaded downloads\n" +
            "• Proxy support for IP rotation\n" +
            "• Session persistence\n" +
            "• Lazy-load media extraction\n\n" +
            "This guide will walk you through everything you need to know to get started!",
            
            "Installation on Android\n\n" +
            "Step 1: Install Termux\n" +
            "• Download Termux from F-Droid (NOT Google Play)\n" +
            "• F-Droid link: https://f-droid.org/packages/com.termux/\n\n" +
            "Step 2: Grant Storage Permission\n" +
            "• Open Termux\n" +
            "• Run: termux-setup-storage\n" +
            "• Grant storage access when prompted\n\n" +
            "Step 3: Install Python\n" +
            "• Run: pkg update\n" +
            "• Run: pkg install python -y\n\n" +
            "Step 4: Install Dependencies\n" +
            "• pip install requests beautifulsoup4 curl-cffi cloudscraper Pillow",
            
            "Running the Scraper\n\n" +
            "Step 1: Open Termux\n" +
            "• Tap the Termux app icon\n\n" +
            "Step 2: Navigate to Home\n" +
            "• Run: cd ~\n\n" +
            "Step 3: Start the Scraper\n" +
            "• Run: python Duke2_Enhanced.py\n\n" +
            "Step 4: Follow Prompts\n" +
            "• Enter the website URL\n" +
            "• Choose media types to download\n" +
            "• Configure scraping parameters\n" +
            "• Wait for downloads to complete\n\n" +
            "Step 5: View Results\n" +
            "• Downloads save to: /sdcard/Download/Duke2/\n" +
            "• View gallery.html in your browser",
            
            "Configuration Options\n\n" +
            "Starting URL\n" +
            "• Enter the website to scrape (e.g., https://example.com)\n\n" +
            "Media Types\n" +
            "• 1 = Images (jpg, png, gif, webp, etc.)\n" +
            "• 2 = Videos (mp4, webm, mkv, etc.)\n" +
            "• 3 = Audio (mp3, ogg, wav, flac, etc.)\n" +
            "• 4 = Documents (pdf, epub, docx, etc.)\n" +
            "• 5 = Archives (zip, rar, 7z, etc.)\n" +
            "• 6 = eBooks (mobi, azw3, cbz, etc.)\n" +
            "• 7 = All types\n\n" +
            "Minimum File Size\n" +
            "• Set minimum file size in KB (0 = no minimum)\n\n" +
            "Crawl Depth\n" +
            "• How many levels of links to follow (default 2)\n\n" +
            "Max Pages\n" +
            "• Maximum number of pages to crawl (default 100)",
            
            "Understanding Media Types\n\n" +
            "Images\n" +
            "• Supported: jpg, png, gif, webp, svg, bmp, tiff, avif\n" +
            "• Includes: lazy-loaded images, background images\n\n" +
            "Videos\n" +
            "• Supported: mp4, webm, mkv, flv, avi, mov, 3gp\n" +
            "• Includes: embedded YouTube, Vimeo links\n\n" +
            "Audio\n" +
            "• Supported: mp3, ogg, wav, flac, aac, m4a, opus\n\n" +
            "Documents\n" +
            "• Supported: pdf, epub, docx, txt, doc, rtf, odt\n\n" +
            "Archives\n" +
            "• Supported: zip, rar, 7z, tar, gz, bz2, xz\n\n" +
            "eBooks\n" +
            "• Supported: mobi, azw3, azw, cbz, cbr",
            
            "Gallery Viewer\n\n" +
            "After Scraping\n" +
            "• A gallery.html file is automatically created\n" +
            "• Contains all downloaded media organized by type\n\n" +
            "Features\n" +
            "• Tab Navigation - Switch between media types\n" +
            "• Search - Filter files by name\n" +
            "• Sort - Sort by name, size, or type\n" +
            "• Lazy Loading - Images load as you scroll\n" +
            "• Fullscreen - Click to view items fullscreen\n" +
            "• Download Button - Re-download individual files\n\n" +
            "Gallery Location\n" +
            "• /sdcard/Download/Duke2/gallery.html\n\n" +
            "Opening the Gallery\n" +
            "• Tap 'Open Gallery' button in this app\n" +
            "• Or open in any web browser",
            
            "Cloudflare Bypass\n\n" +
            "Three Available Engines\n\n" +
            "1. curl_cffi (Recommended)\n" +
            "• TLS fingerprint impersonation\n" +
            "• Mimics Chrome browser at network level\n" +
            "• Best for most Cloudflare-protected sites\n\n" +
            "2. cloudscraper\n" +
            "• Solves JavaScript challenges\n" +
            "• Good for moderate protection\n\n" +
            "3. Standard requests (Fallback)\n" +
            "• Basic HTTP without bypass\n" +
            "• Works on unprotected sites\n\n" +
            "Selection\n" +
            "• Choose 'Auto' for best available engine\n" +
            "• The scraper will handle fallback automatically",
            
            "Proxy Configuration\n\n" +
            "When to Use Proxies\n" +
            "• Avoid IP bans on rate-limited sites\n" +
            "• Access geo-restricted content\n" +
            "• Rotate IPs for large-scale scraping\n\n" +
            "Proxy Types Supported\n" +
            "• HTTP: http://proxy.example.com:8080\n" +
            "• SOCKS5: socks5://user:pass@proxy.com:1080\n" +
            "• Authenticated: http://user:pass@host:port\n\n" +
            "Configuration\n" +
            "• When prompted, enter proxy URL\n" +
            "• Leave blank for no proxy\n\n" +
            "Free Proxy Sources\n" +
            "• proxy-list.download\n" +
            "• free-proxy-list.net\n" +
            "• geonode.com/free-proxy-list",
            
            "Tips & Tricks\n\n" +
            "Performance\n" +
            "• Increase crawl depth for more content\n" +
            "• Set minimum file size to skip small files\n" +
            "• Use multi-threading for faster downloads\n\n" +
            "Session Persistence\n" +
            "• Cookies are saved automatically\n" +
            "• Re-run for authenticated sites\n" +
            "• Saves to ~/.duke2_cookies.json\n\n" +
            "Large Downloads\n" +
            "• Results are auto-zipped as media.zip\n" +
            "• Easy to transfer or backup\n\n" +
            "Debugging\n" +
            "• Check /sdcard/Download/Duke2/ for details\n" +
            "• results.json and results.csv contain metadata",
            
            "Troubleshooting\n\n" +
            "Cannot Open Gallery\n" +
            "• Ensure scraper completed successfully\n" +
            "• Check /sdcard/Download/Duke2/ exists\n\n" +
            "Rate Limited (429)\n" +
            "• Increase delays between requests\n" +
            "• Use proxy for IP rotation\n" +
            "• Reduce crawl depth\n\n" +
            "Access Denied (403)\n" +
            "• Try different Cloudflare bypass engine\n" +
            "• Use proxy\n" +
            "• Check User-Agent rotation\n\n" +
            "Python Errors\n" +
            "• Ensure all dependencies installed\n" +
            "• Run: pip install --upgrade requests beautifulsoup4 curl-cffi\n\n" +
            "No Storage Permission\n" +
            "• Run: termux-setup-storage in Termux\n" +
            "• Grant permission when prompted"
        };
    }
    
    private void updateContent() {
        TextView tvContent = findViewById(1);
        tvContent.setText(tutorialTitles[currentStep] + "\n\n" + tutorialContent[currentStep]);
    }
}
