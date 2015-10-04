import Foundation

/**
* Execute block on the main thread, asynchronously
*/
func dispatch_to_main(block: dispatch_block_t) {
    dispatch_async(dispatch_get_main_queue(), block)
}

/**
* Execute block on default priority background queue, asynchronously
*/
func dispatch_to_bg(block: dispatch_block_t)
{
    let background_queue =
    dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0)
    
    dispatch_async(background_queue, block)
}

/**
* Execute block on main queue after delay seconds.
* Returns optional token that can be used to cancel dispatch by calling
* cancel_dispatch
*/
func dispatch_later(delay: NSTimeInterval,
    block: dispatch_block_t) -> (Bool -> ())?
{
    var cancelled = false
    
    let wrapper = { (cancel: Bool) in
        if (cancel) {
            cancelled = true
            return
        }
        
        if (!cancelled) {block()}
    }
    
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW,
        Int64(delay * NSTimeInterval(NSEC_PER_SEC))),
        dispatch_get_main_queue(),
        {wrapper(false)})
    return wrapper;
}

/**
* Cancels block scheduled by dispatch_later
*/
func cancel_dispatch(wrapper: (Bool -> ()))
{
    wrapper(true)
}
