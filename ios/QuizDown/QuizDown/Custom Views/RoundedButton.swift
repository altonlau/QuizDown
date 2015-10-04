import UIKit
import Foundation

class RoundedButton: UIButton {
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)!
        self.layer.cornerRadius = 4.0;
        self.layer.borderColor = self.tintColor.CGColor
        self.layer.borderWidth = 1
    }
    
}