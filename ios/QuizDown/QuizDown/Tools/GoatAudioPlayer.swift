import AVFoundation
import Foundation

class GoatAudioPlayer {
    
    private var goatAudioPlayer = AVAudioPlayer()
    var isPlaying: Bool {
        get {
            return goatAudioPlayer.playing
        }
    }
    var numberOfLoops: Int {
        get {
            return goatAudioPlayer.numberOfLoops
        }
        set(value) {
            goatAudioPlayer.numberOfLoops = value
        }
    }
    
    init() {
        let goatAudioURL = NSURL(fileURLWithPath: NSBundle.mainBundle().pathForResource("screaming_goat", ofType: "mp3")!)
        
        do {
            try AVAudioSession.sharedInstance().setCategory(AVAudioSessionCategoryPlayback)
            try goatAudioPlayer = AVAudioPlayer(contentsOfURL: goatAudioURL)
            goatAudioPlayer.prepareToPlay()
        } catch {
            print("Can't get audio file.")
        }
    }
    
    func play() {
        if (!isPlaying) {
            goatAudioPlayer.numberOfLoops = numberOfLoops
            goatAudioPlayer.play()
        }
    }
    
    func stop() {
        goatAudioPlayer.stop()
    }
    
}