package com.lz.kotin.base_library.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide.init

/**
 * Fragment基类
 *
 * author: lovelz
 * date: on 2020-07-27
 */
abstract class BaseVmFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var mActivity: AppCompatActivity
    private var dataBindingConfig: DataBindingConfig? = null
    private var mBinding: ViewDataBinding? = null

    private var activityProvider: ViewModelProvider? = null
    private var fragmentProvider: ViewModelProvider? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as AppCompatActivity
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getLayoutId()?.let {
            val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, it, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
            dataBindingConfig = getDataBindingConfig()
            dataBindingConfig?.apply {
                val bindingParams = getBindingParams()
                for (i in 0 until bindingParams.size()) {
                    binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
                }

            }
            mBinding = binding
            return binding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent(savedInstanceState)
        observer()
        onClick()
    }

    abstract fun initEvent(savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int?

    abstract fun getDataBindingConfig(): DataBindingConfig?

    open fun initViewModel() {

    }

    open fun observer() {

    }

    open fun onClick() {}

    protected fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T {
        if (activityProvider == null) {
            activityProvider = ViewModelProvider(mActivity)
        }
        return activityProvider!!.get(modelClass)
    }

    protected fun <T : ViewModel?> getFragmentViewModel(modelClass: Class<T>): T {
        if (fragmentProvider == null) {
            fragmentProvider = ViewModelProvider(this)
        }
        return fragmentProvider!!.get(modelClass)
    }

    protected fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

}