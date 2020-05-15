package com.spd.im

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spd.im.core.ImClient
import com.spd.im.core.entity.ImEntity
import com.spd.im.core.listener.ILoginCallback
import com.spd.im.core.listener.ImMessageListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ImMessageListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImClient.getInstance().init(this)
        ImClient.getInstance().login("123456", "", object : ILoginCallback {
            override fun onSuccess() {
                log.text = "登录成功"
                ImClient.getInstance().chatManager.addMessageListener(this@MainActivity)
            }

            override fun onFailed(e: Exception) {
                log.text = "登录失败"
            }
        })
    }

    override fun onDestroy() {
        ImClient.getInstance().chatManager.removeMessageListener(this)
        ImClient.getInstance().logout()
        super.onDestroy()
    }

    override fun onMessageReceived(message: ImEntity) {
        log.text = message.toString()
    }
}