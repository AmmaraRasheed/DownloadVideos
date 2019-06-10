package com.example.rabia.downloadyoutubevideo;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

public class MainActivity extends AppCompatActivity {

    String downloadLink="https://youtu.be/QhL-Rv5VH7o";
    String newLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void DownloadMyVideo(View view){
        YouTubeUriExtractor youTubeUriExtractor=new YouTubeUriExtractor(MainActivity.this) {
            @Override
            public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles) {

                if(ytFiles!=null){
                    int tag=22;
                    newLink=ytFiles.get(tag).getUrl();
                    String title="MyDrama";
                    DownloadManager.Request request=new DownloadManager.Request(Uri.parse(newLink));
                    request.setTitle(title);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title+".mp4");
                    DownloadManager downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                    request.allowScanningByMediaScanner();
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    downloadManager.enqueue(request);

                }

            }
        };

        youTubeUriExtractor.execute(downloadLink);
    }
}
