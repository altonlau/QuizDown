import AVFoundation
import Foundation
import UIKit

class MainViewController: UIViewController {
    
    var goatAudioPlayer = GoatAudioPlayer()

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func otherButtonPressed(sender: AnyObject) {
        goatAudioPlayer.play()
    }
    
}

