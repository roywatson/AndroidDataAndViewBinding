/*
Copyright (C) 2022 Roy Watson

Permission is hereby granted, free of charge, to any person obtaining a copy of this
software and associated documentation files (the "Software"), to deal in the Software
without restriction, including without limitation the rights to use, copy, modify, merge,
publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies
or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
OR OTHER DEALINGS IN THE SOFTWARE.
*/
package com.delasystems.androiddataandviewbinding.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean


class MainViewModel : ViewModel() {

    // WATSON:livedata for xml layout to observe
    private val _buttonText = MutableLiveData<String>("Start")
    val buttonText: LiveData<String> = _buttonText

    // WATSON:livedata for xml layout to observe
    private val _countText = MutableLiveData<String>("0")
    val countText: LiveData<String> = _countText

    var continueCounting = AtomicBoolean(false)
    var count = 0

    fun buttonClick() {
        viewModelScope.launch(Dispatchers.IO) {
            if(!continueCounting.get()) {
                continueCounting.set(true)
                // WATSON:since we are setting value in coroutine we must use "postValue"
                _buttonText.postValue("Stop")
                while(continueCounting.get()) {
                    count++
                    // WATSON:since we are setting value in coroutine we must use "postValue"
                    _countText.postValue(count.toString())
                    delay(1000)
                }
            } else {
                continueCounting.set(false)
                // WATSON:since we are setting value in coroutine we must use "postValue"
                _buttonText.postValue("Start")
            }
        }
    }
}