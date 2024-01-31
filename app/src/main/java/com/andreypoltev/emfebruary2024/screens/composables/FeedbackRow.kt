package com.andreypoltev.emfebruary2024.screens.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.data.model.Feedback

@Composable
fun FeedbackRow(feedback: Feedback) {

    Row {
        // TODO "Если в модели ответа пришел объект feedback, то отображается шкала рейтинга: 5 звезд. Заполнение звезд зависит от значения поля feedback.rating. Значение 0 - все звезды пустые, значение 5 - все звезды залиты."
        Text(text = feedback.rating.toString())// тут звёздочки

        Spacer(modifier = Modifier.size(8.dp))

        Row {
            Text(text = feedback.rating.toString())// а тут просто число
            Text(text = " * ") // TODO Тут должна быть точка 2*2
            // TODO "Число отзывов берется из поля feedback.count и отображается в формате: “{feedback.count} отзыва”. В зависимости от значения feedback.count, последнее слово склоняется: “отзыв”, “отзыва”, “отзывов”. Если объект feedback отсутствует в ответе, то число отзывов, шкала рейтинга и рейтинг не отображаются."
            Text(text = "${feedback.count} отзыва(вов)") // TODO Склоняется

        }


    }

}