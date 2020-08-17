package io.github.slflfl12.movieapp.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import io.github.slflfl12.presentation.base.BaseViewModel

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {

    protected lateinit var binding: B

    abstract val vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.setVariable(BR.vm, vm)
        binding.lifecycleOwner = this
    }

    override fun onPause() {
        vm.unbindViewModel()
        super.onPause()
    }

    protected fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun showKeyboard() {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,0
        )
    }

    fun hideKeyboard() {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                (currentFocus ?: View(this)).windowToken, 0
        )
    }

}