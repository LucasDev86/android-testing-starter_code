package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.core.Is.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        // 활성 작업 만들기
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = false)
        )
        // 함수 호출
        val result = getActiveAndCompletedStats(tasks)
        // 결과 확인
//        assertEquals(result.completedTasksPercent , 0f)
//        assertEquals(result.activeTasksPercent, 100f)
        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_noActive_returnZeroHundred() {
        val task = listOf<Task>(
                Task("title", "desc", isCompleted = true)
        )

        val result = getActiveAndCompletedStats(task)

        assertEquals(result.activeTasksPercent, 0f)
        assertEquals(result.completedTasksPercent, 100f)
    }

    @Test
    fun getActiveAndCompletedStats_error_returnZeros() {

        val result = getActiveAndCompletedStats(emptyList())

        assertEquals(result.activeTasksPercent, 0f)
        assertEquals(result.completedTasksPercent, 0f)
    }

}

