package com.example.admin2.echo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.admin2.echo.R
import com.example.admin2.echo.activities.MainActivity
import com.example.admin2.echo.fragments.AboutUsFragment
import com.example.admin2.echo.fragments.FavoriteFragment
import com.example.admin2.echo.fragments.MainScreenFragment
import com.example.admin2.echo.fragments.SettingsFragment

class NavigationDrawerAdapter(_contentList: ArrayList<String>, _getImages: IntArray, _context: Context) :
        RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>() {
    var _contentList: ArrayList<String>? = null
    var _getImages: IntArray? = null
    var mContext: Context? = null

    init {
        this._contentList = _contentList
        this._getImages = _getImages
        this.mContext = _context
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        holder?.icon_Get?.setBackgroundResource(_getImages?.get(position) as Int)
        holder?.text_Get?.setText(_contentList?.get(position))
        holder?.contentHolder?.setOnClickListener({
            if (FavoriteFragment.Statified.mediaPlayer !=null&& FavoriteFragment.Statified.mediaPlayer!!.isPlaying){
                FavoriteFragment.Statified.mediaPlayer!!.stop()
            }

            if (position == 0) {
                val mainScreenFragment = MainScreenFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, mainScreenFragment)
                        .commit()
            } else if (position == 1) {
                val favoriteFragment = FavoriteFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, favoriteFragment)
                        .commit()
            } else if (position == 2) {
                val settingsFragment = SettingsFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, settingsFragment)
                        .commit()
            } else {
                val aboutUsFragment = AboutUsFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, aboutUsFragment)
                        .commit()
            }
            MainActivity.Statified.drawerLayout?.closeDrawers()
        })

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {
        var itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_navigationdrawer, parent, false)
        val returnThis = NavViewHolder(itemView)
        return returnThis
    }

    override fun getItemCount(): Int {
        return (_contentList as ArrayList).size
    }


    class NavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon_Get: ImageView? = null
        var text_Get: TextView? = null
        var contentHolder: RelativeLayout? = null

        init {
            icon_Get = itemView?.findViewById(R.id.icon_navdrawer)
            text_Get = itemView?.findViewById(R.id.text_navdrawer)
            contentHolder = itemView?.findViewById(R.id.navdrawer_item_content_holder)
        }

    }
}