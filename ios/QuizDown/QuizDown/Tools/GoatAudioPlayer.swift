import AVFoundation
import Foundation

protocol GoatAudioPlayerDelegate: class {
    func goatAudioDidFinishPlaying()
}

class GoatAudioPlayer: NSObject, AVAudioPlayerDelegate {
    
    private var goatAudioPlayer = AVAudioPlayer()
    weak var delegate: GoatAudioPlayerDelegate?
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
    
    override init() {
        super.init()
        
        let goatAudioURL = NSURL(fileURLWithPath: NSBundle.mainBundle().pathForResource("screaming_goat", ofType: "mp3")!)
        
        do {
            try AVAudioSession.sharedInstance().setCategory(AVAudioSessionCategoryPlayback)
            try goatAudioPlayer = AVAudioPlayer(contentsOfURL: goatAudioURL)
            goatAudioPlayer.delegate = self
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
    
    // MARK - AVAudioPlayerDelegate Methods
    
    func audioPlayerDidFinishPlaying(player: AVAudioPlayer, successfully flag: Bool) {
        delegate?.goatAudioDidFinishPlaying()
    }
    
}