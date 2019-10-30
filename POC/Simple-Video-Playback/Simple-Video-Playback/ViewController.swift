//
//  ViewController.swift
//  Simple-Video-Playback
//
//  Created by Hanna Joseph on 22/10/19.
//  Copyright © 2019 Infosys. All rights reserved.
//

import UIKit
import BrightcovePlayerSDK

// ** Customize these values with your own account information **
let kViewControllerPlaybackServicePolicyKey = "BCpkADawqM084SHJARkakPt3GAxbBPIUIlpdK94IcBqbXUno62npqE_EYOoUCmFpqho6IxGuV9y7AzWFTvXmiuGA6DDNBG1EmrWLSPh9GijRPvcxJCQmElYncjO4jY3xLJQbt--uWalt9nMu"
let kViewControllerAccountID = "6094789246001"
let kViewControllerVideoID = "6096529492001"

class ViewController: UIViewController, BCOVPlaybackControllerDelegate {

    let sharedSDKManager = BCOVPlayerSDKManager.shared()
    let playbackService = BCOVPlaybackService(accountId: kViewControllerAccountID, policyKey: kViewControllerPlaybackServicePolicyKey)!
    let playbackController :BCOVPlaybackController
    
    @IBOutlet weak var videoContainerView: UIView!

    required init?(coder aDecoder: NSCoder) {
        playbackController = (sharedSDKManager?.createPlaybackController())!
        
        super.init(coder: aDecoder)
        
        playbackController.analytics.account = kViewControllerAccountID // Optional
        
        playbackController.delegate = self
        playbackController.isAutoAdvance = true
        playbackController.isAutoPlay = true
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        // Set up our player view. Create with a standard VOD layout.
        guard let playerView = BCOVPUIPlayerView(playbackController: self.playbackController, options: nil, controlsView: BCOVPUIBasicControlView.withVODLayout()) else {
            return
        }
        
        // Install in the container view and match its size.
        self.videoContainerView.addSubview(playerView)
        playerView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            playerView.topAnchor.constraint(equalTo: self.videoContainerView.topAnchor),
            playerView.rightAnchor.constraint(equalTo: self.videoContainerView.rightAnchor),
            playerView.leftAnchor.constraint(equalTo: self.videoContainerView.leftAnchor),
            playerView.bottomAnchor.constraint(equalTo: self.videoContainerView.bottomAnchor)
            ])
        
        // Associate the playerView with the playback controller.
        playerView.playbackController = playbackController
        
        requestContentFromPlaybackService()
        //getUploadedFilesList()
    }
    
    func requestContentFromPlaybackService() {
        playbackService.findVideo(withVideoID: kViewControllerVideoID, parameters: nil) { (video: BCOVVideo?, jsonResponse: [AnyHashable: Any]?, error: Error?) -> Void in
            
            if let v = video {
                self.playbackController.setVideos([v] as NSArray)
            } else {
                print("ViewController Debug - Error retrieving video: \(error?.localizedDescription ?? "unknown error")")
            }
        }
    }
 
}

