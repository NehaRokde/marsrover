package com.example.listellanasaapp.ui.marsrover

import androidx.compose.runtime.Composable
import com.example.listellanasaapp.ui.marsrover.curosity.CuriosityScreen
import com.example.listellanasaapp.ui.marsrover.spirit.SpiritScreen


typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Curiosity : TabItem( "Curiosity", { CuriosityScreen() })
    object Opportunity : TabItem("Opportunity", { OpportunityScreen() })
    object Spirit : TabItem( "Spirit", { SpiritScreen() })
}
