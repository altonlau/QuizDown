import Dispatch
import Foundation
import UIKit

class LoadingViewController: UIViewController {
    
    var pViewController = QuizViewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        getQuestions { (result) -> () in
            let resultArray = result as! NSArray
            
            var questions = [Question]()
            
            for data in resultArray {
                let qdata = data as! NSDictionary
                let question = Question(answer: (qdata["answer"] as! String?)!, question: (qdata["question"] as! String?)!)
                questions.append(question)
            }
            
            self.pViewController.questionArray = self.shuffleArray(questions)
            self.dismissViewControllerAnimated(true, completion: { () -> Void in
                self.pViewController.startQuiz();
            })
        }
    }
    
    func getQuestions(completion: (AnyObject?) -> ()) {
        let url = NSURL( string: "http://gtesting.comule.com/sqldata.php")
        let request = NSMutableURLRequest( URL: url!)
        request.HTTPMethod = "GET"
        
        let task = NSURLSession.sharedSession().dataTaskWithRequest(request) {
            data, response, error in
            
            if error != nil {
                print(error)
                return
            } else {
                var json: AnyObject? = nil
                
                do {
                    try json = NSJSONSerialization.JSONObjectWithData(data!, options: .MutableContainers)
                } catch {
                    print("Could not parse JSON.")
                }
                
                completion(json!["data"])
            }
        }
        task.resume()
    }
    
    func shuffleArray<T>(var array: Array<T>) -> Array<T> {
        for var index = array.count - 1; index > 0; index-- {
            // Random int from 0 to index-1
            let j = Int(arc4random_uniform(UInt32(index-1)))
            
            // Swap two array elements
            // Notice '&' required as swap uses 'inout' parameters
            swap(&array[index], &array[j])
        }
        return array
    }
}