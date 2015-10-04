import Foundation
import UIKit

protocol AnswerLabelDelegate: class {
    func didSelectAnswer(answerLabel: AnswerLabel)
}

class AnswerLabel: RoundedLabel {
    
    weak var delegate: AnswerLabelDelegate?
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        self.layer.cornerRadius = 4.0;
        self.layer.borderColor = self.tintColor.CGColor
        self.layer.borderWidth = 1
        
        let tapGesture = UITapGestureRecognizer(target: self, action: "labelTouched:")
        self.addGestureRecognizer(tapGesture)
    }
    
    func labelTouched(recognizer: UITapGestureRecognizer) {
        delegate?.didSelectAnswer(self)
    }
}