package prateek_gupta.foody.ui.fragments.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import prateek_gupta.foody.databinding.FragmentInstructionsBinding
import prateek_gupta.foody.models.Result
import prateek_gupta.foody.util.Constants

class InstructionsFragment : Fragment() {
    private var _binding:FragmentInstructionsBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle = args?.getParcelable(Constants.RECIPE_RESULT_KEY) as Result?

        //view.instructions_webView.webViewClient = object : WebViewClient() {}
        val websiteUrl: String = myBundle!!.sourceUrl
        binding.instructionsWebView.loadUrl(websiteUrl)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}