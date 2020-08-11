/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.architecture.blueprints.todoapp

import androidx.lifecycle.Observer

/**
 * 이벤트를 나타내는 LiveData를 통해 노출되는 데이터의 래퍼로 사용됩니다.
 */
open class Event<out T>(private val content: T) {

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set //외부 읽기는 허용하지만 쓰기는 허용하지 않음

    /**
     * 콘텐츠를 반환하고 다시 사용하지 못하도록합니다.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * 이미 처리 된 경우에도 콘텐츠를 반환합니다.
     */
    fun peekContent(): T = content
}

/**
 * [이벤트]에 대한 [관찰자], [이벤트]의 내용에
 * 이미 처리되었습니다.
 *
 * [onEventUnhandledContent]는 [Event]의 내용이 처리되지 않은 경우 * only * 호출됩니다.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}
