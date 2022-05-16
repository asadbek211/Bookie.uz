package com.bizmiz.bookieuz.ui.main.player

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentAudioPlayerBinding
import com.bizmiz.bookieuz.ui.model.AudioDetails


class AudioPlayerFragment : Fragment() {
    private lateinit var binding: FragmentAudioPlayerBinding
    private var mediaPlayer: MediaPlayer? = null
    private var isRepeat = true
    private var isVolume = true
    private var isFavourite = true
    private var check = false
    private val handler = Handler()
    private var mPosition: Int = 0
    private var mCurrentPosition: Int = 0
    private var audios: List<AudioDetails>? = null
    private var bookName = ""
    private var bookAuthor = ""
    private var dublyajAuthor = ""
    private var audioUrlsList: ArrayList<String> = arrayListOf()
    private val sectionList: ArrayList<String> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (requireArguments().get("audios") != null) {
            audios = requireArguments().get("audios") as List<AudioDetails>?
        }
        bookName = requireArguments().getString("bookName", "")
        bookAuthor = requireArguments().getString("bookAuthor", "")
        dublyajAuthor = requireArguments().getString("dublyajAuthor", "")
        binding = FragmentAudioPlayerBinding.bind(
            inflater.inflate(
                R.layout.fragment_audio_player,
                container,
                false
            )
        )
        binding.loading.setOnClickListener {  }
        binding.tvBookTitle.text = bookName
        binding.tvBookAuthor.text = bookAuthor
        binding.tvActors.text = "Dublyaj aktyorı: ${dublyajAuthor}"
        if (audios != null) {
            audios!!.forEach {
                audioUrlsList.add(it.audio)
                sectionList.add(it.title)
            }
        }
        val adapter = ArrayAdapter(requireActivity(), R.layout.spinner_item, sectionList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spSection.adapter = adapter
        binding.imgPlayPause.setOnClickListener {
            if (audioUrlsList.isNotEmpty()) {
                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.pause()
                    binding.imgPlayPause.setImageResource(R.drawable.ic_play)
                } else {
                    mediaPlayer!!.start()
                    binding.imgPlayPause.setImageResource(R.drawable.ic_pause)
                }
            } else {
                Toast.makeText(requireActivity(), "Audiolar yo'q", Toast.LENGTH_SHORT).show()
            }
        }
        binding.ivClose.setOnClickListener {
            if (check) {
                mediaPlayer!!.stop()
            }
            val navController =
                Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.popBackStack()
        }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer!!.seekTo(progress * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        activity?.runOnUiThread(object : Runnable {
            override fun run() {
                if (mediaPlayer != null) {
                    if (binding.tvStartTime.text.contains(binding.tvEndTime.text) && !binding.tvEndTime.text.contains(
                            "0:00"
                        )
                    ) {
                        if (isRepeat){
                            mediaPlayer!!.seekTo(0)
                            mediaPlayer!!.start()
                        }else{
                            mediaPlayer!!.seekTo(0)
                            binding.imgPlayPause.setImageResource(R.drawable.ic_play)
                        }
                    }
                    binding.imgNext.setOnClickListener {
                        mPosition = mediaPlayer!!.currentPosition + 1000
                        mediaPlayer!!.seekTo(mPosition)
                    }
                    binding.imgPrevious.setOnClickListener {
                        mPosition = mediaPlayer!!.currentPosition - 1000
                        mediaPlayer!!.seekTo(mPosition)
                    }
                    mCurrentPosition = mediaPlayer!!.currentPosition / 1000
                    binding.seekBar.progress = mCurrentPosition
                    binding.tvStartTime.text = formattedTime(mCurrentPosition)
                }
                handler.postDelayed(this, 1000)
            }
        })
        binding.imgFavouriteAdd.setOnClickListener {
            if (isFavourite) {
                binding.imgFavouriteAdd.setImageResource(R.drawable.ic_bookmark_on)
                isFavourite = false
            } else {
                binding.imgFavouriteAdd.setImageResource(R.drawable.ic_favourite_add)
                isFavourite = true
            }
        }
        binding.imgRepeatMode.setOnClickListener {
            if (isRepeat) {
                binding.imgRepeatMode.setImageResource(R.drawable.ic_repeat_off)
                isRepeat = false
            } else {
                binding.imgRepeatMode.setImageResource(R.drawable.ic_repeat_on)
                isRepeat = true
            }
        }
        binding.imgVolume.setOnClickListener {
            if (isVolume) {
                binding.imgVolume.setImageResource(R.drawable.ic_volume_off)
                isVolume = false
            } else {
                binding.imgVolume.setImageResource(R.drawable.ic_volume_on)
                isVolume = true
            }
        }
        binding.spSection.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                check = true
                binding.loading.visibility = View.VISIBLE
                if (mediaPlayer!=null && mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.stop()
                }
                binding.imgPlayPause.setImageResource(R.drawable.ic_play)
                if (mediaPlayer != null) {
                    mediaPlayer = null
                }
                mediaPlayer = MediaPlayer()
                audioCreate(audioUrlsList[position])
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }
        return binding.root
    }

    private fun formattedTime(mCurrentPosition: Int): String? {
        var totalout = ""
        var totalNew = ""
        val seconds = (mCurrentPosition % 60).toString()
        val minutes = (mCurrentPosition / 60).toString()
        totalout = "$minutes:$seconds"
        totalNew = "$minutes:0$seconds"
        return if (seconds.length == 1) {
            totalNew
        } else {
            totalout
        }
    }

    private fun audioCreate(audioUrl: String) {
        if (mediaPlayer != null) {
            mediaPlayer!!.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            mediaPlayer!!.setOnPreparedListener {
                it.start()
                binding.imgPlayPause.setImageResource(R.drawable.ic_pause)
                binding.loading.visibility = View.GONE
                binding.tvEndTime.text = formattedTime((mediaPlayer!!.duration) / 1000)
                binding.seekBar.max = (mediaPlayer!!.duration) / 1000
            }
            mediaPlayer!!.setDataSource(audioUrl)
            mediaPlayer!!.prepareAsync()
        }
    }
}