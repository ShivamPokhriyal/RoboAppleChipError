package com.example.roboapplechiperror

import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLooper

@RunWith(RobolectricTestRunner::class)
class SampleViewTest {
    private val view = SampleView(ApplicationProvider.getApplicationContext())
    private val listener = TestListener()

    @Test
    fun testView() {
        view.setListener(listener)
        assertThat(listener.called).isFalse()

        view.append("1")
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
        assertThat(listener.called).isTrue()
    }


    class TestListener : SampleView.Listener {

        var called = false
        override fun changed() {
            called = true
        }

    }
}
