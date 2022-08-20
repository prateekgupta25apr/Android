package prateek_gupta.foody.util;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class MyExtensionFunctions<T> {
    public void observeOnce(LiveData<T> liveDataObject, LifecycleOwner lifecycleOwner,
                            Observer<T> observer){
        // Calling observe() method to add the Observer object from LifecycleOwner
        // observers list
        liveDataObject.observe(lifecycleOwner, new Observer<T>() {
            @Override
            public void onChanged(T t) {
                // Calling removeObserver() method to remove the Observer object from
                // LifecycleOwner observers list
                liveDataObject.removeObserver(this);

                // Calling onChanged() method to just execute the lambda expression provided
                // for Observer object
                observer.onChanged(t);
            }
        });
    }
}
