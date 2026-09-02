package com.mulheres

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FolderAdapter(
    private val onClick: (File) -> Unit
) : RecyclerView.Adapter<FolderAdapter.VH>() {

    private var list: List<File> = emptyList()

    class VH(view: View) : RecyclerView.ViewHolder(view) {

        val icon: ImageView =
            view.findViewById(R.id.fileIcon)

        val name: TextView =
            view.findViewById(R.id.folderName)

        val date: TextView =
            view.findViewById(R.id.folderDate)

        val count: TextView =
            view.findViewById(R.id.folderCount)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_folder,
                parent,
                false
            )

        return VH(view)
    }

    override fun onBindViewHolder(
        holder: VH,
        position: Int
    ) {

        val file = list[position]

        // =====================================================
        // FONTE
        // =====================================================

        val fonte = try {

            Typeface.createFromAsset(
                holder.itemView.context.assets,
                "font.ttf"
            )

        } catch (e: Exception) {

            Typeface.DEFAULT
        }

        holder.name.typeface = fonte
        holder.date.typeface = fonte
        holder.count.typeface = fonte


        // =====================================================
        // NOME
        // =====================================================

        holder.name.text =
            file.name.ifEmpty {
                "Sem nome"
            }


        // =====================================================
        // DATA
        // =====================================================

        holder.date.text =
            SimpleDateFormat(
                "dd/MM/yyyy HH:mm",
                Locale.getDefault()
            ).format(
                Date(file.lastModified())
            )


        // =====================================================
        // DIFERENÇA ENTRE PASTA E ARQUIVO
        // =====================================================

        if (file.isDirectory) {

            // PASTA

            holder.icon.setImageResource(
                R.drawable.ic_folder
            )

            val quantidade =
                file.listFiles()?.size ?: 0

            holder.count.text =
                if (quantidade == 1) {
                    "1 item"
                } else {
                    "$quantidade itens"
                }

        } else {

            // ARQUIVO

            holder.icon.setImageResource(
                R.drawable.ic_document
            )

            holder.count.text =
                "Arquivo"
        }


        // =====================================================
        // CLIQUE
        // =====================================================

        holder.itemView.setOnClickListener {

            onClick(file)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(newList: List<File>) {

        list = newList

        notifyDataSetChanged()
    }
}