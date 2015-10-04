import AVFoundation
import Foundation
import UIKit

class QuizViewController: UIViewController, AnswerLabelDelegate {
    
    @IBOutlet weak var questionLabel: UILabel!
    @IBOutlet weak var answerLabel1: AnswerLabel!
    @IBOutlet weak var answerLabel2: AnswerLabel!
    @IBOutlet weak var answerLabel3: AnswerLabel!
    @IBOutlet weak var answerLabel4: AnswerLabel!
    var goatAudioPlayer = GoatAudioPlayer()
    var questionArray = [Question]()
    var correctAnswerIndex = 0
    var currentQuestionNumber = 0
    let randomStringAnswers = ["Que?", "2", "Life", "Me no understand!?", "WTF!?", "8=👊🏻=D💦", "ಠ_ಠ", "( •_•)\n( •_•)>⌐■-■\n(⌐■_■) #Yeeeaaahhh", "(ノಠ益ಠ)ノ彡┻━┻", "ᶘ ᵒᴥᵒᶅ"]
    
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
            nextQuestion()
        }
    }
    
    // MARK - AnswerLabelDelegate Methods
    func didSelectAnswer(answerLabel: AnswerLabel) {
        var selectedAnswerIndex = -1
        
        switch answerLabel {
        case answerLabel1:
            selectedAnswerIndex = 0
        case answerLabel2:
            selectedAnswerIndex = 1
        case answerLabel3:
            selectedAnswerIndex = 2
        case answerLabel4:
            selectedAnswerIndex = 3
        default:
            selectedAnswerIndex = -1
        }
        
        if selectedAnswerIndex == correctAnswerIndex {
            nextQuestion()
        } else {
            goatAudioPlayer.play()
        }
    }
    
    // MARK - Private Methods
    
    private func nextQuestion() {
        if questionArray.count > 0 {
            navigationController?.navigationBar.topItem?.title = "Question \(String(++currentQuestionNumber))"
            
            let index = Int(arc4random_uniform(UInt32(questionArray.count)))
            setupQuestion(questionArray.removeAtIndex(index))
        }
    }
    
    private func setupQuestion(question: Question) {
        questionLabel.text = question.question
        
        correctAnswerIndex = Int(arc4random_uniform(4))
    
        answerLabel1.text = generateRandomAnswer(question.answer)
        answerLabel2.text = generateRandomAnswer(question.answer)
        answerLabel3.text = generateRandomAnswer(question.answer)
        answerLabel4.text = generateRandomAnswer(question.answer)
        
        switch correctAnswerIndex {
        case 0:
            answerLabel1.text = question.answer
        case 1:
            answerLabel2.text = question.answer
        case 2:
            answerLabel3.text = question.answer
        default:
            answerLabel4.text = question.answer
        }
    }
    
    private func generateRandomAnswer(answer: String) -> String {
        let randomIntAnswer = Int(answer)
        let randomFloatAnswer = Float(answer)
        
        if randomIntAnswer != nil {
            return String(randomIntAnswer! + Int(arc4random_uniform(11)) - 5)
        } else if randomFloatAnswer != nil {
            let decimalPlace = (Float(arc4random()) / 0x100000000);
            return String(randomFloatAnswer! + Float(arc4random_uniform(11)) - 5 + decimalPlace)
        }
        
        return randomStringAnswers[Int(arc4random_uniform(UInt32(randomStringAnswers.count)))]
    }
}