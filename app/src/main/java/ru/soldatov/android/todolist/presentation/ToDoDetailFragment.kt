package ru.soldatov.android.todolist.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.soldatov.android.todolist.databinding.ItemTaskFragmentBinding
import ru.soldatov.android.todolist.presentation.viewModel.ToDoItemViewModel
import ru.soldatov.android.todolist.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class ToDoDetailFragment : Fragment() {

    lateinit var viewModel: ToDoItemViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as ToDoApplication).component
    }

    private var _binding: ItemTaskFragmentBinding? = null
    private val binding: ItemTaskFragmentBinding
        get() = _binding ?: throw RuntimeException("ItemTaskFragmentBinding == null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ToDoItemViewModel::class.java]
        binding.flabAddTaskDone.setOnClickListener {
            viewModel.addToDoItem(
                binding.titleTask.text?.toString(),
                binding.descriptionTask.text?.toString()
            )
            Log.d("Test", binding.titleTask.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}