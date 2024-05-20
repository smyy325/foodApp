package com.example.kaufmanns

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Notification_Bottom_Fragment : BottomSheetDialogFragment() {
    private var mp: MediaPlayer? = null
    private var currentSong = mutableListOf(R.raw.a111)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification__bottom_, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab_play = view.findViewById<FloatingActionButton>(R.id.fab_play)
        val fab_pause = view.findViewById<FloatingActionButton>(R.id.fab_pause)
        val fab_stop = view.findViewById<FloatingActionButton>(R.id.fab_stop)
        val seekbar = view.findViewById<SeekBar>(R.id.seekbar)

        controlSound(currentSong[0], fab_play, fab_pause, fab_stop, seekbar)
        initialiseSeekBar(seekbar)
    }

    private fun controlSound(
        id: Int,
        fab_play: FloatingActionButton,
        fab_pause: FloatingActionButton,
        fab_stop: FloatingActionButton,
        seekbar: SeekBar
    ) {
        fab_play.setOnClickListener {
            if (mp == null) {
                mp = MediaPlayer.create(requireContext(), id)
                Log.d("Notification_Bottom_Fragment", "ID:${mp!!.audioSessionId}")
                initialiseSeekBar(seekbar)
            }
            mp?.start()
            Log.d("Notification_Bottom_Fragment", "Duration:${mp!!.duration / 1000}seconds")
        }

        fab_pause.setOnClickListener {
            if (mp != null) {
                mp?.pause()
                Log.d(
                    "Notification_Bottom_Fragment",
                    "Paused at: ${mp!!.currentPosition / 1000}seconds"
                )
            }
        }

        fab_stop.setOnClickListener {
            if (mp != null) {
                mp?.pause()
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp = null
            }
        }
    }

    private fun initialiseSeekBar(seekbar: SeekBar) {
        seekbar.max = mp?.duration ?: 0
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekbar.progress = mp?.currentPosition ?: 0
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    seekbar.progress = 0
                }
            }
        }, 0)
    }


}