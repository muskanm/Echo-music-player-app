package com.example.admin2.echo.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.example.admin2.echo.Songs
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.admin2.echo.R

import com.example.admin2.echo.activities.MainActivity
import com.example.admin2.echo.fragments.FavoriteFragment.Statified.mediaPlayer
import com.example.admin2.echo.fragments.MainScreenFragment
import com.example.admin2.echo.fragments.SongPlayingFragment
import kotlinx.android.synthetic.main.row_custom_mainscreen_adapter.*


class MainScreenAdapter(_songDetails: ArrayList<Songs>, _context: Context) : RecyclerView.Adapter<MainScreenAdapter.MyViewHolder>() {


    var _songDetails: ArrayList<Songs>? = null
    var _mContext: Context? = null

    init {
        this._songDetails = _songDetails
        this._mContext = _context
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songObject = _songDetails?.get(position)
        holder.trackTitle?.text = songObject?.songTitle
        holder.trackArtist?.text = songObject?.artist

        holder.contentHolder?.setOnClickListener({
            if (SongPlayingFragment.Statified.mediaplayer != null && SongPlayingFragment.Statified.mediaplayer!!.isPlaying()) {
                SongPlayingFragment.Statified.mediaplayer!!.stop()

            }


            val songPlayingFragment = SongPlayingFragment()

            var args = Bundle()
            args.putString("songArtist", songObject?.artist)
            args.putString("path", songObject?.songData)
            args.putString("songTitle", songObject?.songTitle)
            args.putInt("SongId", songObject?.songID?.toInt() as Int)
            args.putInt("songPosition", position)
            args.putParcelableArrayList("songData", _songDetails)
            songPlayingFragment.arguments = args
            (_mContext as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, songPlayingFragment)
                    .addToBackStack("SongPlayingFragment")
                    .commit()

        })


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_mainscreen_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (_songDetails == null) {
            return 0
        } else {
            return (_songDetails as ArrayList<Songs>).size
        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var trackTitle: TextView? = null
        var trackArtist: TextView? = null
        var contentHolder: RelativeLayout? = null

        init {
            trackTitle = view.findViewById<TextView>(R.id.trackTitle)
            trackArtist = view.findViewById<TextView>(R.id.trackArtist)
            contentHolder = view.findViewById<RelativeLayout>(R.id.contentRow)
        }

    }


}
