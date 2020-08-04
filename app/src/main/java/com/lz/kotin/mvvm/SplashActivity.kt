package com.lz.kotin.mvvm

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.lz.kotin.base_library.base.BaseVmActivity
import com.lz.kotin.base_library.utils.DialogUtils
import com.lz.kotin.mvvm.play.PlayerManager
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

/**
 * author: lovelz
 * date: on 2020-07-22
 */
class SplashActivity : BaseVmActivity(), EasyPermissions.PermissionCallbacks {

    private var disposable: Disposable? = null
    private val tips = "玩安卓现在要向您申请存储权限，用于访问您的本地音乐，您也可以在设置中手动开启或者取消。"
    private val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun initEvent(savedInstanceState: Bundle?) {
        requestPermission()
    }

    private fun requestPermission() {
        if (EasyPermissions.hasPermissions(this, *perms)) {
            startIntent()
        } else {
            DialogUtils.tips(this, tips) {
                requestLocationAndCallPermission()
            }
        }
    }

    private fun startIntent() {
        PlayerManager.instance.init(this)
        disposable = Observable.timer(2000, TimeUnit.MILLISECONDS)
            .subscribe {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    override fun getLayoutId(): Int? = R.layout.activity_splash

    @AfterPermissionGranted(WRITE_EXTERNAL_STORAGE)
    private fun requestLocationAndCallPermission() {
        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            startIntent()
        } else {
            EasyPermissions.requestPermissions(this, "请求写入权限", WRITE_EXTERNAL_STORAGE, *perms)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        startIntent()
    }

    companion object {
        private const val WRITE_EXTERNAL_STORAGE = 100
    }
}