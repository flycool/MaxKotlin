package com.kotliin.maxkotlin.feature_alum.presentation.albumlist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.kotliin.library_base.delegate.observer
import com.kotliin.library_base.presentation.extension.setOnDebouncedClickListener
import com.kotliin.maxkotlin.feature_alum.domain.model.AlbumDomainModel
import com.kotlin.maxkotlin.feature_alum.R
import com.pawegio.kandroid.show
import kotlinx.android.synthetic.main.fragment_album_detail.view.*
import kotlinx.android.synthetic.main.fragment_album_list_item.view.*

/**
 * create by max at 2020/10/4 14:52
 *
 */

internal class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.MyViewHoder>() {

    var albums: List<AlbumDomainModel> by observer(listOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((album: AlbumDomainModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHoder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_album_list_item, parent, false)
        return MyViewHoder(view)
    }

    override fun onBindViewHolder(holder: MyViewHoder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size

    fun setOnDebouncedClickListener(listener: (album: AlbumDomainModel) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    internal inner class MyViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var url by observer<String?>(null) {
            if (it == null) {
                setDefaultImage()
            } else {
                itemView.coverImageView.load(it) {
                    crossfade(true)
                    error(R.drawable.ic_image)
                    transformations(RoundedCornersTransformation(10F))
                }
            }
        }


        fun bind(albumDomainModel: AlbumDomainModel) {
            itemView.setOnDebouncedClickListener { onDebouncedClickListener?.invoke(albumDomainModel) }
            url = albumDomainModel.getDefaultImageUrl()
        }

        private fun setDefaultImage() {
            itemView.coverErrorImageView.show()
        }
    }

}

