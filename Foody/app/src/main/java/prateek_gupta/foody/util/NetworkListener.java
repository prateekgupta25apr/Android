package prateek_gupta.foody.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class NetworkListener extends ConnectivityManager.NetworkCallback {
    MutableLiveData<Boolean> isNetworkAvailable=new MutableLiveData<>();

    public NetworkListener() {
        isNetworkAvailable.setValue(false);
    }

    public MutableLiveData<Boolean> checkNetworkAvailability(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this);
        }
        else {
            NetworkRequest.Builder networkRequestBuilder=new NetworkRequest.Builder();
            networkRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR);
            networkRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
            networkRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET);
            connectivityManager.registerNetworkCallback(networkRequestBuilder.build(),this);
        }

        boolean isConnected=false;
        for (Network network:connectivityManager.getAllNetworks()){
            NetworkCapabilities networkCapabilities= connectivityManager.getNetworkCapabilities(network);
            if (networkCapabilities!=null){
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    isConnected = true;
                }
            }
        }

        isNetworkAvailable.setValue(isConnected);
        return isNetworkAvailable;
    }

    @Override
    public void onAvailable(@NonNull Network network) {
        isNetworkAvailable.postValue(true);
    }

    @Override
    public void onLost(@NonNull Network network) {
        isNetworkAvailable.postValue(false);
    }
}
