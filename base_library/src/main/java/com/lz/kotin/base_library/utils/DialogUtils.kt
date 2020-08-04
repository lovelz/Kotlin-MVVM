package com.lz.kotin.base_library.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.lz.kotin.base_library.R

/**
 * 简单的确认提示对话框
 *
 * author: lovelz
 * date: on 2020-07-22
 */
class DialogUtils {

    companion object {
        private var dialog: Dialog? = null

        /**
         * 二次确认对话框
         */
        fun confirm(context: Context, tips: String, onClick: (View) -> Unit) {
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_confirm, null)
            view.findViewById<TextView>(R.id.confirm_content).apply {
                text = tips
            }
            view.findViewById<TextView>(R.id.tv_cancel).clickNoRepeat {
                dismiss()
            }
            view.findViewById<TextView>(R.id.tv_sure).clickNoRepeat {
                onClick.invoke(view)
                dismiss()
            }
            AlertDialog.Builder(context).apply {
                setView(view)
                dialog = create()
            }
            dialog?.show()
        }

        /**
         * 提示对话框
         */
        fun tips(context: Context, tips: String, onClick: (View) -> Unit) {
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_confirm, null)
            view.findViewById<TextView>(R.id.confirm_content).apply {
                text = tips
            }
            view.findViewById<TextView>(R.id.tv_cancel).apply {
                visibility = View.GONE
            }
            view.findViewById<TextView>(R.id.tv_sure).clickNoRepeat {
                onClick.invoke(view)
                dismiss()
            }
            AlertDialog.Builder(context).apply {
                setView(view)
                dialog = create()
            }
            dialog?.show()
        }

        fun showLoading(context: Context, msg: String?) {
            AlertDialog.Builder(context).apply {
                setView(
                    LayoutInflater.from(context).inflate(R.layout.dialog_loading, null).apply {
                        findViewById<TextView>(R.id.loading_msg).text = msg
                    }
                )
                dialog = this.create()
            }
            dialog?.show()
        }

        fun dismiss() {
            dialog?.dismiss()
            dialog = null
        }
    }

}