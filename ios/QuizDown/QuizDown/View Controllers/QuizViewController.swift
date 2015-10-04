import AVFoundation
import Foundation
import UIKit

class QuizViewController: UIViewController, AnswerLabelDelegate {
    
    @IBOutlet weak var answerLabel1: AnswerLabel!
    @IBOutlet weak var answerLabel2: AnswerLabel!
    @IBOutlet weak var answerLabel3: AnswerLabel!
    @IBOutlet weak var answerLabel4: AnswerLabel!
    var goatAudioPlayer = GoatAudioPlayer()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.hidesBackButton = true
        
        answerLabel1.delegate = self
        answerLabel2.delegate = self
        answerLabel3.delegate = self
        answerLabel4.delegate = self
    }
    
    @IBAction func otherButtonPressed(sender: AnyObject) {
        goatAudioPlayer.play()
    }
    
    // MARK - AnswerLabelDelegate Methods
    func didSelectAnswer(answerLabel: AnswerLabel) {
        switch (answerLabel) {
        case answerLabel1:
            print("Answer 1")
        case answerLabel2:
            print("Answer 2")
        case answerLabel3:
            print("Answer 3")
        case answerLabel4:
            print("Answer 4")
        default:
            return
        }
    }
    
    // MARK - Private Methods
    
}