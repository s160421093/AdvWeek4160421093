import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.advweek4160421093.databinding.FragmentStudentDetailBinding
import com.ubaya.advweek4160421093.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        // Call fetch function
        viewModel.refresh()

        // Observe Student LiveData
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer { students ->
            // Assuming you only want to display the first student in the list
            if (students.isNotEmpty()) {
                val student = students[0] // Get the first student
                // Update EditText values
                binding.txtID.setText(student.id)
                binding.txtName.setText(student.name)
                binding.txtBod.setText(student.bod)
                binding.txtPhone.setText(student.phone)
            }
        })
    }
}