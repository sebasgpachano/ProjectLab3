package com.example.sprint3.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    var binding: B? = null

    private lateinit var baseActivity: BaseActivity<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity<*>
    }

    override fun onResume() {
        super.onResume()
        configureToolbarAndConfigScreenSections()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        inflateBinding()
        createViewAfterInflateBinding(inflater, container, savedInstanceState)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeViewModel()
        viewCreatedAfterSetupObserverViewModel(view, savedInstanceState)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    fun hideToolbar() {
        baseActivity.hideToolbar()
    }

    fun showToolbar(
        showBack: Boolean = false,
        title: String = "",
    ) {
        baseActivity.showToolbar(
            showBack = showBack,
            title = title,
        )
    }

    fun updateShowToolbarBack(showBack: Boolean) {
        baseActivity.updateShowToolbarBack(showBack)
    }

    fun updateShowToolbarTitle(title: String) {
        baseActivity.updateShowToolbarTitle(title)
    }

    fun showLoading(show: Boolean) {
        baseActivity.showLoading(show)
    }


    fun fragmentFullScreenLayoutWithoutToolbar() {
        baseActivity.fragmentFullScreenLayoutWithoutToolbar()
    }

    fun fragmentLayoutWithToolbar() {
        baseActivity.fragmentLayoutWithToolbar()
    }

    fun hideKeyboard() {
        baseActivity.hideKeyboard()
    }

    abstract fun inflateBinding()
    abstract fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )

    abstract fun configureToolbarAndConfigScreenSections()

    protected open fun setupViewModel() = Unit

    abstract fun observeViewModel()

    abstract fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?)

}