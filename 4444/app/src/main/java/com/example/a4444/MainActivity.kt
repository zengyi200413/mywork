package com.example.a4444

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private var nameTextView: TextView? = null
    private var genderTextView: TextView? = null
    private var phoneTextView: TextView? = null
    private var scoreTextView: TextView? = null
    private var avatarImageView: ImageView? = null
    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化视图组件
        nameTextView = findViewById(R.id.nameTextView)
        genderTextView = findViewById(R.id.genderTextView)
        phoneTextView = findViewById(R.id.phoneTextView)
        scoreTextView = findViewById(R.id.scoreTextView)
        avatarImageView = findViewById(R.id.avatarImageView)

        // 假设Player对象已经初始化好
        player = Player(
            "张三",
            "男",
            "1234567890",
            "zhangsan@example.com",
            "阅读 运动 音乐",

        )

        // 设置玩家信息
        updatePlayerInfo()

        // 修改信息按钮点击事件
        val editButton = findViewById<Button>(R.id.editButton)
        editButton.setOnClickListener {
            val intent =
                Intent(
                    this@MainActivity,
                    EditInfoActivity::class.java
                )
            intent.putExtra("player", player)
            someActivityResultLauncher.launch(intent)
        }

        // 开始游戏按钮点击事件
        val startGameButton = findViewById<Button>(R.id.startGameButton)
        startGameButton.setOnClickListener {
            val intent =
                Intent(
                    /* packageContext = */ this@MainActivity,
                    /* cls = */ GameActivity::class.java
                )
            startActivity(intent)
        }
    }

    private val someActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            // 如果信息修改成功，更新玩家信息
            player = result.data?.getParcelableExtra("player")
            updatePlayerInfo()
        }
    }

    private fun updatePlayerInfo() {
        nameTextView!!.text = player!!.name
        genderTextView!!.text = player!!.gender
        phoneTextView!!.text = player!!.phone
        scoreTextView!!.text = player!!.score

        if (player!!.avatarPath != null && !player!!.avatarPath?.isEmpty()!!) {
            val bitmap = BitmapFactory.decodeFile(player!!.avatarPath)
            avatarImageView!!.setImageBitmap(bitmap)
        } else {
            avatarImageView!!.setImageResource(R.drawable.default_avatar) // 默认头像
        }
    }
}