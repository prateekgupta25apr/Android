package prateek_gupta.foody.ui.fragments.foodJoke;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dagger.hilt.android.AndroidEntryPoint;
import prateek_gupta.foody.R;
import prateek_gupta.foody.databinding.FragmentFoodJokeBinding;
import prateek_gupta.foody.util.Constants;
import prateek_gupta.foody.util.NetworkResult;
import prateek_gupta.foody.viewmodels.MainViewModel;

@AndroidEntryPoint
public class FoodJokeFragment extends Fragment {

    public MainViewModel mainViewModel;

    FragmentFoodJokeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentFoodJokeBinding.inflate(inflater,container,false);
        binding.setLifecycleOwner(this.getViewLifecycleOwner());
        binding.setMainViewModel(mainViewModel);

        mainViewModel.getFoodJoke(Constants.API_KEY);
        mainViewModel.foodJokeResponse.observe(this.getViewLifecycleOwner(),response->{

            if (response instanceof NetworkResult.Success){
                binding.foodJokeTextView.setText(response.data.getText());
            }else if (response instanceof NetworkResult.Error){
                loadDataFromCache();
                Toast.makeText(requireContext(),response.message,Toast.LENGTH_SHORT).show();
            }
            else if (response instanceof NetworkResult.Loading){
                Log.d("FoodJokeFragment", "Loading");
            }
        });

        return binding.getRoot();
    }

    void loadDataFromCache(){
        mainViewModel.readFoodJoke.observe(getViewLifecycleOwner(),database->{
            if (database!=null && database.size()>0)
                binding.foodJokeTextView.setText(
                        database.get(0).getFoodJoke().getText());
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}