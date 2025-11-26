package com.example.myapplication.Movies

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.BuildConfig.IMAGE_BASE_URL
import com.example.myapplication.R
import com.example.myapplication.databinding.MovieDetailBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MovieDetailsBottomSheet: BottomSheetDialogFragment() {

    private var _binding: MovieDetailBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieDetailBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title") ?: ""
        val description = arguments?.getString("description") ?: ""
        val posterPath = arguments?.getString("imagePath") ?: ""

        binding.bottomSheetTitle.text = title
        binding.bottomSheetDescription.text = description
        val radius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            8f,
            context?.resources?.displayMetrics
        ).toInt()

        Glide.with(requireContext())
            .load(IMAGE_BASE_URL + posterPath)
            .apply(
                RequestOptions().transform(
                    CenterCrop(), // optional: scales the image
                    RoundedCornersTransformation(
                        radius,
                        0,
                        RoundedCornersTransformation.CornerType.TOP
                    )
            ))
            .into(binding.bottomSheetImage)
    }

    companion object{
        fun newInstance(title: String, description: String, imagePath: String): MovieDetailsBottomSheet{
            val fragment = MovieDetailsBottomSheet()
            val bundle = Bundle()
            bundle.putString("title", title)
            bundle.putString("description", description)
            bundle.putString("imagePath", imagePath)
            fragment.arguments = bundle
            return fragment
        }
    }
}