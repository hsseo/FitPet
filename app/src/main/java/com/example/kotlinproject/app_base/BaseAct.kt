package com.example.kotlinproject.app_base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.kotlinproject.ui.dlg.DlgLoading
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseAct<BINDING: ViewBinding, VM: BaseViewModel>() : AppCompatActivity() {

    protected var viewModel : VM ?= null
    protected var _binding : BINDING ?= null

    val binding get() = _binding!!
    var subscription : CompositeDisposable = CompositeDisposable()

    protected var dlgLoading : DlgLoading?= null

    protected abstract fun createViewModel() : VM
    protected abstract fun createViewBinding(layoutInflater : LayoutInflater) : BINDING


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = createViewBinding(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = createViewModel()

        initObserveViewModel()

        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        subscription?.let {
            if(it.isDisposed) {
                subscription.dispose()
            }
        }
        EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    public fun replce(resID:Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(resID, fragment).commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun funEventBus(data : BaseData) {
        //TODO : eventbus func create!
//        receiveEventBus(data)
    }

    fun sendEventBus(sendDt : BaseData) {
        EventBus.getDefault().post(sendDt)
    }

    fun initObserveViewModel() {
        viewModel?.let {
            it.showErrorMessageLiveData().observe(this, Observer{
                toast(it)
            })
            it.showLoadingLiveData().observe(this, Observer{
                showLoading()
            })
            it.hideLoadingLiveData().observe(this, Observer{
                dismissLoading()
            })
        }
    }

    fun toast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        showLoading("")
    }

    fun showLoading(message:String) {
        if(dlgLoading == null) {
            dlgLoading = DlgLoading(this)
        }
        dlgLoading!!.message(message)
    }

    fun dismissLoading() {
        if(dlgLoading != null) {
            try {
                dlgLoading!!.dismiss()
            } catch (err:Exception) {}
        }
    }

    private val imagePickerLauncher = registerImagePicker {
        //TODO : picker func create!!
//        picker(it)
        sendEventBus(BaseData())
    }

    fun launchImageMultiPicker(bCamera : Boolean, selCnt : Int, limtCnt : Int) {
        launchImagePicker(bCamera, selCnt, limtCnt, "","","")
    }

    fun launchSingImagePicker(bCamera : Boolean, fTitle : String, iTitle : String, bTitle : String) {
        launchImagePicker(bCamera, 1, 1, fTitle, iTitle, bTitle)
    }

    fun launchImagePicker(bCamera : Boolean, selCnt : Int, limtCnt : Int, fTitle : String, iTitle : String, bTitle : String) {
        imagePickerLauncher.launch(ImagePickerConfig {
            mode = if (selCnt > 1) ImagePickerMode.MULTIPLE else ImagePickerMode.SINGLE
            isShowCamera = bCamera
            returnMode = if (selCnt > 1) ReturnMode.NONE else ReturnMode.ALL
            isFolderMode = true
            folderTitle = fTitle
            imageTitle = iTitle
            doneButtonText = bTitle
            isIncludeVideo = true
            limit = limtCnt
        })
    }
}