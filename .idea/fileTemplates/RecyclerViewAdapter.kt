#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

#parse("File Header.java")

class ${NAME} (val items: List<${ITEM_CLASS}>, val itemClick: (${ITEM_CLASS}) -> Unit) : RecyclerView.Adapter<${NAME}.${VIEWHOLDER_CLASS}>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ${VIEWHOLDER_CLASS} {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.${LAYOUT_RES_ID}, parent, false)
        return ${VIEWHOLDER_CLASS}(view, itemClick)
    }

    override fun onBindViewHolder(holder: ${VIEWHOLDER_CLASS}, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    class ${VIEWHOLDER_CLASS}(val view: View, val itemClick: (${ITEM_CLASS}) -> Unit) : RecyclerView.ViewHolder(view) {

        // here comes all the views / variables from view.findViewById()
        // Example: val textView = view.findViewById(R.id.textView1)
        fun bindView(item: ${ITEM_CLASS}) {
            with(item) {
                // here you do all kind of assignments and logics..
                // for Example: textView.text = item.name or just name 
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

}