import AVFoundation
import Foundation
import UIKit

class QuizViewController: UIViewController, AnswerLabelDelegate {
    
    @IBOutlet weak var questionTextView: UITextView!
    @IBOutlet weak var answerLabel1: AnswerLabel!
    @IBOutlet weak var answerLabel2: AnswerLabel!
    @IBOutlet weak var answerLabel3: AnswerLabel!
    @IBOutlet weak var answerLabel4: AnswerLabel!
    var goatAudioPlayer = GoatAudioPlayer()
    var questionArray = [Question]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.hidesBackButton = true
        
        answerLabel1.delegate = self
        answerLabel2.delegate = self
        answerLabel3.delegate = self
        answerLabel4.delegate = self
        
        let loadingViewController = storyboard?.instantiateViewControllerWithIdentifier("LoadingIdentifier") as! LoadingViewController
        loadingViewController.pViewController = self
        presentViewController(loadingViewController, animated: false, completion: nil)
    }
    
    @IBAction func otherButtonPressed(sender: AnyObject) {
        goatAudioPlayer.play()
    }
    
    func startQuiz() {
        if questionArray.count > 0 {
            setupQuestion(questionArray.removeFirst())
        }
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
    
    private func setupQuestion(question: Question) {
        let boldAttribute = [
            NSFontAttributeName: UIFont.boldSystemFontOfSize(28.0),
            NSForegroundColorAttributeName: UIColor.whiteColor()
        ]
        let attributedQuestion = NSAttributedString(string: question.question, attributes: boldAttribute)
        questionTextView.attributedText = attributedQuestion
        questionTextView.textAlignment = .Center
        
        answerLabel1.text = question.answer
        answerLabel2.text = question.answer
        answerLabel3.text = question.answer
        answerLabel4.text = question.answer
    }
    
    private func generateRandomAnswer(answer: Int) {
        
    }
}