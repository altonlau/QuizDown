import AVFoundation
import Foundation
import UIKit

class QuizViewController: UIViewController {
    
    var goatAudioPlayer = GoatAudioPlayer()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.hidesBackButton = true
    }
    
    @IBAction func otherButtonPressed(sender: AnyObject) {
        goatAudioPlayer.play()
    }
    
}