package com.example.carDock.presentation.dashBoard.fragments.carReg.commponents


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.carDock.ui.theme.MyColors

@Composable

fun FilteredTextField(
    label: String,
    value: String,
    isError : Boolean = false,
    modifier: Modifier = Modifier.padding(all = 0.dp),
    list: List<String>,
    onSelected: (obj: String) -> String,
    onChange: (text: String) -> Unit
) {

    var expandedState by rememberSaveable {
        mutableStateOf(
            false
        )
    }

    val textState = rememberSaveable {
        mutableStateOf(
            value
        )
    }


    Column(modifier = modifier) {

        RegFormTextFiled(
            value = textState.value,
            label = label,
            error = isError,
            onValueChange =
            {
                expandedState = true
                textState.value = it
                onChange(textState.value)
            },
            onFocusChanged =
            {
                if (!it) {
                    expandedState = false
                } else {
                    expandedState = true
                    onChange(textState.value)
                }
            }

        )

        if (expandedState && list.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .height(if (list.size * 50 < 200) (list.size * 60).dp else 200.dp)
                    .padding(all = 10.dp)
            ) {
                Text(text = "Select a $label", modifier = Modifier.weight(1f))

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(list)
                    {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 2.dp),
                            onClick =
                            {
                                textState.value = onSelected(it)
                                expandedState = false
                            }
                        ) {
                            Text(
                                text = it, color = MyColors.primaryDark,
                                modifier = Modifier.align(Alignment.CenterVertically)

                            )

                        }
                    }

                }
            }
        }


    }
}