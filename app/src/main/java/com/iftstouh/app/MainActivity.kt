
package com.iftstouh.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.FrameLayout
import android.graphics.drawable.GradientDrawable

class MainActivity : Activity() {

    private val cream = Color.rgb(248, 244, 235)
    private val blue = Color.rgb(48, 75, 99)
    private val gold = Color.rgb(193, 153, 76)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = cream
        window.navigationBarColor = cream
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR

        showSplash()
    }

    private fun makeText(
        value: String,
        size: Float,
        color: Int,
        bold: Boolean = false
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            gravity = Gravity.CENTER
            if (bold) setTypeface(null, Typeface.BOLD)
            textDirection = View.TEXT_DIRECTION_RTL
        }
    }

    private fun showSplash() {
        val root = FrameLayout(this)
        root.setBackgroundColor(cream)

        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 24, 28, 24)
        }

        val logo = TextView(this).apply {
            text = "✦\n📖"
            textSize = 38f
            gravity = Gravity.CENTER
            setTextColor(blue)
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.rgb(234, 226, 210))
                setStroke(3, gold)
            }
        }

        content.addView(logo, LinearLayout.LayoutParams(150, 150))

        val title = makeText(
            "المعهد الوطني المتخصص في التكوين المهني\nالإخوة شتوح",
            23f, blue, true
        )
        val titleParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        titleParams.setMargins(0, 30, 0, 16)
        content.addView(title, titleParams)

        val subtitle = makeText("✦  ✦  ✦", 18f, gold)
        content.addView(subtitle)

        val loading = TextView(this).apply {
            text = "◌"
            textSize = 42f
            setTextColor(blue)
            gravity = Gravity.CENTER
        }
        val loadingParams = LinearLayout.LayoutParams(80, 80)
        loadingParams.setMargins(0, 18, 0, 20)
        content.addView(loading, loadingParams)

        val rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 1000
            repeatCount = Animation.INFINITE
        }
        loading.startAnimation(rotate)

        root.addView(
            content,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        val credit = makeText("برمجة عبد الجبار", 13f, blue)
        val creditParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, 60,
            Gravity.BOTTOM
        )
        root.addView(credit, creditParams)

        setContentView(root)

        root.postDelayed({
            loading.clearAnimation()
            showHome()
        }, 7000)
    }

    private fun showHome() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(24, 36, 24, 24)
            setBackgroundColor(cream)
        }

        val heading = makeText(
            "المعهد الوطني المتخصص في التكوين المهني الإخوة شتوح",
            23f, blue, true
        )
        root.addView(
            heading,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        val sub = makeText(
            "تخصص إعلام آلي — الدعم التقني وصيانة المعلوماتية",
            15f, blue
        )
        val subParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        subParams.setMargins(0, 14, 0, 35)
        root.addView(sub, subParams)

        addCard(root, "البرنامج") {
            showMessage("البرنامج", "سيتم إضافة برامج السداسيات الخمسة هنا.")
        }

        addCard(root, "الدروس") {
            showMessage("الدروس", "سيتم إضافة المواد والدروس هنا.")
        }

        setContentView(root)
    }

    private fun addCard(
        parent: LinearLayout,
        label: String,
        action: () -> Unit
    ) {
        val card = TextView(this).apply {
            text = label
            textSize = 22f
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
            setTextColor(blue)
            background = GradientDrawable().apply {
                setColor(Color.WHITE)
                cornerRadius = 28f
                setStroke(2, gold)
            }
            elevation = 5f
            setOnClickListener { action() }
        }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 130
        )
        params.setMargins(0, 12, 0, 12)
        parent.addView(card, params)
    }

    private fun showMessage(title: String, message: String) {
        android.app.AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("حسنًا", null)
            .show()
    }
}
