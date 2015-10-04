import UIKit
import Foundation

class RoundedLabel: UILabel {
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)!
        self.layer.cornerRadius = 8.0;
        self.layer.borderColor = self.tintColor.CGColor
        self.layer.borderWidth = 1
    }
    
}