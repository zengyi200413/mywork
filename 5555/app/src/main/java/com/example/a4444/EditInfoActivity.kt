package com.example.a4444

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class EditInfoActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var hobbyReadingCheckBox: CheckBox
    private lateinit var hobbySportsCheckBox: CheckBox
    private lateinit var hobbyMusicCheckBox: CheckBox
    private lateinit var avatarImageView: ImageView
    private var player: Player? = null

    @SuppressLint("IntentReset")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_info)

        // 获取传递过来的Player对象
        player = intent.getParcelableExtra("player", Player::class.java)

        // 初始化视图组件
        nameEditText = findViewById(R.id.nameEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        hobbyReadingCheckBox = findViewById(R.id.hobbyReadingCheckBox)
        hobbySportsCheckBox = findViewById(R.id.hobbySportsCheckBox)
        hobbyMusicCheckBox = findViewById(R.id.hobbyMusicCheckBox)
        avatarImageView = findViewById(R.id.avatarImageView)

        // 设置编辑框初始值
        player?.let {
            nameEditText.setText(it.name)
            phoneEditText.setText(it.phone)

            // 设置性别初始值
            val maleRadioButton = findViewById<RadioButton>(R.id.maleRadioButton)
            val femaleRadioButton = findViewById<RadioButton>(R.id.femaleRadioButton)
            when (it.gender) {
                "男" -> maleRadioButton.isChecked = true
                "女" -> femaleRadioButton.isChecked = true
            }

            // 设置头像初始值
            if (!it.avatarPath.isNullOrEmpty()) {
                val bitmap = BitmapFactory.decodeFile(it.avatarPath)
                avatarImageView.setImageBitmap(bitmap)
            } else {
                avatarImageView.setImageResource(R.drawable.card_matched_image) // 默认头像
            }
        }

        // 上传照片按钮点击事件
        val uploadPhotoButton = findViewById<Button>(R.id.uploadPhotoButton)
        uploadPhotoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            photoActivityResultLauncher.launch(intent)
        }

        // 保存按钮点击事件
        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            player?.apply {
                name = nameEditText.text.toString()
                phone = phoneEditText.text.toString()

                val selectedId = genderRadioGroup.checkedRadioButtonId
                gender = when (selectedId) {
                    R.id.maleRadioButton -> "男"
                    R.id.femaleRadioButton -> "女"
                    else -> gender
                }

                // 处理爱好选择
                hobbies = buildString {
                    if (hobbyReadingCheckBox.isChecked) append("阅读 ")
                    if (hobbySportsCheckBox.isChecked) append("运动 ")
                    if (hobbyMusicCheckBox.isChecked) append("音乐 ")
                }.trim()
            }

            val data = Intent()
            data.putExtra("player", player)
            setResult(RESULT_OK, data)
            finish()
        }
    }

    private val photoActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val selectedImageUri = result.data?.data
            selectedImageUri?.let {
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
                    avatarImageView.setImageBitmap(bitmap)
                    player?.avatarPath = getRealPathFromURI(it)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getRealPathFromURI(contentUri: Uri): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri, proj, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            it.moveToFirst()
            return it.getString(columnIndex)
        }
        return null
    }
}