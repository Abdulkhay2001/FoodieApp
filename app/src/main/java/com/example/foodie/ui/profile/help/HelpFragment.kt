package com.example.foodie.ui.profile.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodie.databinding.FragmentHelpBinding
import com.example.foodie.databinding.HelpItemBinding

class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icHelp.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.icHelp.imgShoppingCart.isVisible = false
        binding.icHelp.tvRoot.text = "Help"
        binding.icHelp.back.isVisible = true

        val list: MutableList<HelpQuestion> = mutableListOf(
            HelpQuestion(
                "Когда вы доставляете?",
                "Доставка по Самаре осуществляется ежедневно. Вы сможете выбрать удобный промежуток времени: с 11:00 до 22:00. Мы позвоним заранее и уточним время доставки.",
                false
            ),
            HelpQuestion("Сколько стоит доставка?", "Доставка всегда бесплатна.", false),
            HelpQuestion(
                " В какие города вы доставляете?",
                "Пока мы доставляем только по г. Самаре. Но в ближайшее время мы расширим список городов доставки. Подпишитесь на нас и Вы узнаете об открытии Кило Вкуса в Вашем городе первым!",
                false
            ),
            HelpQuestion(
                "Могу ли я приостановить или изменить время доставки?",
                "Мы понимаем, что обстоятельства могут измениться в любой момент. Позвоните нам и мы найдем решение.",
                false
            ),
            HelpQuestion(
                "Как упакована еда?",
                "Жидкие блюда упакованы в герметичные контейнеры, твердые в бумажные термопакеты, способные сохранить свежесть и вкусовые качества продуктов. Каждый заказ упакован в картонную коробку.",
                false
            ),
            HelpQuestion(
                "Есть ли какие-то добавки?",
                "Никаких консервантов и усилителей вкуса.",
                false
            ),
            HelpQuestion(
                "Какой срок хранения вашей еды?",
                "Замороженные блюда хранятся всего 30 дней, а готовые охлажденные 72 часа.\n" + "Короткие сроки хранения обусловлены полным отсутствием консервантов.",
                false
            )
        )

        list.forEach {
            val itemView = HelpItemBinding.inflate(layoutInflater)
            itemView.question1.text = it.question
            itemView.answer1.text = it.answer
            itemView.question1.setOnClickListener { v ->
                it.flag = !it.flag
                itemView.answer1.isVisible = it.flag
            }
            binding.llHelp.addView(itemView.root)
        }

    }

}