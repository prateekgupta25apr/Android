package prateek_gupta.foody.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    // Calling observe() method to add the Observer object from LifecycleOwner
    // observers list
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            // Calling removeObserver() method to remove the Observer object from
            // LifecycleOwner observers list
            removeObserver(this)

            // Calling onChanged() method to just execute the lambda expression provided
            // for Observer object
            observer.onChanged(t)
        }
    })
}