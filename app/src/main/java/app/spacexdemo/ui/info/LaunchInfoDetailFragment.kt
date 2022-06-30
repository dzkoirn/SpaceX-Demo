package app.spacexdemo.ui.info;

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import app.spacexdemo.R
import com.bumptech.glide.Glide
import spacexdemo.domain.dto.LaunchInfo

class LaunchInfoDetailFragment : DialogFragment() {

    private lateinit var patch: ImageView
    private lateinit var details: TextView
    private lateinit var articleBtn: Button
    private lateinit var wikiBtn: Button
    private lateinit var youtubeBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_launch_detail, container, false)

        patch = view.findViewById(R.id.patch)
        details = view.findViewById(R.id.details)
        articleBtn = view.findViewById(R.id.article_button)
        wikiBtn = view.findViewById(R.id.wiki_button)
        youtubeBtn = view.findViewById(R.id.youtube_button)

        setupView(details, BUNDLE_DETAILS_KEY) { value -> text = value }
        setupView(patch, BUNDLE_PATCH_KEY) { url ->  Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_download_placeholder)
            .into(this) }

        setupView(articleBtn, BUNDLE_ARTICLE_KEY, setupOnclickListenerToOpenUrl)
        setupView(wikiBtn, BUNDLE_WIKIPEDIA_KEY, setupOnclickListenerToOpenUrl)
        setupView(youtubeBtn, BUNDLE_YOUTUBE_ID_KEY) { video_id ->
            setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://$video_id")))
            }
        }

        // There is possible situation when data is not available at all.
        // In this case all view will be not visible
        // Let's handle this situation
        if (arrayOf(patch, details, articleBtn, wikiBtn, youtubeBtn).all { !it.isVisible }) {
            patch.setImageResource(R.drawable.ic_no_photo)
            patch.isVisible = true
            details.text = getString(R.string.no_data_available)
            details.isVisible = true
        }

        return view
    }

    private inline fun <T : View> setupView(view: T, bundleKey: String, action: T.(String) -> Unit) {
        val value = arguments?.getString(bundleKey)
        if (value != null) {
            action(view, value)
        } else {
            view.isVisible = false
        }
    }

    private val setupOnclickListenerToOpenUrl = fun View.(url: String) {
        setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    companion object {

        private const val BUNDLE_DETAILS_KEY = "LaunchInfoDetailFragment_bundle_details"
        private const val BUNDLE_PATCH_KEY = "LaunchInfoDetailFragment_bundle_patch"
        private const val BUNDLE_ARTICLE_KEY = "LaunchInfoDetailFragment_bundle_article"
        private const val BUNDLE_WIKIPEDIA_KEY = "LaunchInfoDetailFragment_bundle_wiki"
        private const val BUNDLE_YOUTUBE_ID_KEY = "LaunchInfoDetailFragment_bundle_youtube"

        fun newInstance(info: LaunchInfo): LaunchInfoDetailFragment {
            return LaunchInfoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_DETAILS_KEY, info.launch.details)
                    putString(BUNDLE_PATCH_KEY, info.launch.links?.patch?.large)
                    putString(BUNDLE_ARTICLE_KEY, info.launch.links?.article)
                    putString(BUNDLE_WIKIPEDIA_KEY, info.launch.links?.wikipedia)
                    putString(BUNDLE_YOUTUBE_ID_KEY, info.launch.links?.youtube_id)
                }
            }
        }
    }
}
