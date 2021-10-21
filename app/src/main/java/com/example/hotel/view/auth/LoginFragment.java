package com.example.hotel.view.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotel.MainActivity;
import com.example.hotel.R;
import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.databinding.FragmentLoginBinding;
import com.example.hotel.model.User;
import com.example.hotel.preferences.UserLoginPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private UserLoginPreferences userLoginPreferences;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        getActivity().setTitle("Login");

        userLoginPreferences = new UserLoginPreferences(binding.getRoot().getContext());

        checkLogin();

        // action untuk btnRegister (pindah ke fragment Register)
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        //action untuk buton Login
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.getUsername();
                String password = binding.getPassword();

                if(getUserLogin(username,password)!= null){

                    userLoginPreferences.setLogin(username,password);

                    if(!getUserLogin(username,password).isUser_status()){
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }else{
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_splashScreenNewMember);
                    }

                    Toast.makeText(binding.getRoot().getContext(), "Login success!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(binding.getRoot().getContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();

    }

    private User getUserLogin(String username, String password){
        User user;
        user = MyDatabaseClient.getInstance(binding.getRoot().getContext())
                    .getDatabase()
                    .userDao()
                    .getUserLogin(username, password);
            return user;
    }

    private void checkLogin() {
        if(userLoginPreferences.checkLogin()){
            startActivity(new Intent(binding.getRoot().getContext(), MainActivity.class));
            getActivity().finish();
        }
    }

}