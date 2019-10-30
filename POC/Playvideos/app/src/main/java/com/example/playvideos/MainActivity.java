package com.example.playvideos;

import androidx.appcompat.app.AppCompatActivity;

import com.brightcove.player.analytics.Analytics;
import com.brightcove.player.edge.Catalog;
import com.brightcove.player.edge.PlaylistListener;
import com.brightcove.player.edge.VideoListener;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Playlist;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;
import com.brightcove.player.view.BrightcovePlayer;
import android.os.Bundle;

import java.net.URISyntaxException;

public class MainActivity extends BrightcovePlayer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        brightcoveVideoView = (BrightcoveExoPlayerVideoView) findViewById(R.id.brightcove_video_view);
//        Analytics analytics = brightcoveVideoView.getAnalytics();
//        analytics.setAccount("6096366634001");
//        Video video = Video.createVideo("https://learning-services-media.brightcove.com/videos/hls/greatblueheron/greatblueheron.m3u8",
//                DeliveryType.HLS);
//
//        try {
//            java.net.URI myposterImage = new java.net.URI("https://solutions.brightcove.com/bcls/assets/images/Great-Blue-Heron.png");
//            video.getProperties().put(Video.Fields.STILL_IMAGE_URI, myposterImage);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//
//        brightcoveVideoView.add(video);
//        brightcoveVideoView.start();




//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        brightcoveVideoView = (BrightcoveExoPlayerVideoView) findViewById(R.id.brightcove_video_view);
//
//        EventEmitter eventEmitter = brightcoveVideoView.getEventEmitter();
//        Catalog catalog = new Catalog(eventEmitter, getString(R.string.account), getString(R.string.policy));
//        catalog.findVideoByID(getString(R.string.videoId), new VideoListener() {
//
//
//            @Override
//            public void onVideo(Video video) {
//                brightcoveVideoView.add(video);
//                brightcoveVideoView.start();
//            }
//
//            @Override
//            public void onError(String s) {
//                throw new RuntimeException(s);
//            }
//        });



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brightcoveVideoView = (BrightcoveExoPlayerVideoView) findViewById(R.id.brightcove_video_view);

        EventEmitter eventEmitter = brightcoveVideoView.getEventEmitter();
        Catalog catalog = new Catalog(eventEmitter, getString(R.string.account), getString(R.string.policy));
        catalog.findPlaylistByID("6096787749001", new PlaylistListener() {
            @Override
            public void onPlaylist(Playlist playlist) {
                brightcoveVideoView.addAll(playlist.getVideos());
                brightcoveVideoView.start();
            }

            @Override
            public void onError(String s) {
                throw new RuntimeException(s);
            }
        });
    }
}
