package com.geniusmind.sport.View;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.Helper.Preferences;
import com.geniusmind.sport.Model.LoginCallback;
import com.geniusmind.sport.Model.UserRegister;
import com.geniusmind.sport.R;
import com.geniusmind.sport.ViewModel.RegisterViewModel;
import com.geniusmind.sport.databinding.FragmentRegisterBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class RegisterFragment extends Fragment implements View.OnClickListener {
     FragmentRegisterBinding binding;
    RegisterViewModel registerViewModel;
    UserRegister userrInfo;
    public static Fragment  getRegisterFragment() {
         return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        userrInfo = new UserRegister() ;

        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_register,container,false);

         registerViewModel = ViewModelProviders.of(getActivity()).get(RegisterViewModel.class);

         // check if image base 64 is empty or not ... then coverted to bitmap . . . ;
        if(registerViewModel.getUserInformation().getImage_base64()!=null) {
            binding.imageUser.setImageBitmap(encodeBase64(registerViewModel.getUserInformation().getImage_base64()));
            userrInfo.setImage_base64(registerViewModel.getBase64());
        }
         // for register button . . . ;
        binding.registerBtn.setOnClickListener(this);
        binding.imageUser.setOnClickListener(this);
        binding.dateBirth.setOnClickListener(this);
        binding.signinLabel.setOnClickListener(this);



        return binding.getRoot();
    }
    // set error for views who is empty . . . ;
    public boolean validateViewsEmpty(int errorId){
        // check if view is empty . . . ;
        binding.fNameLayout.setError(null);
        binding.lNameLayout.setError(null);
        binding.teamNameLayout.setError(null);
        binding.passwordLayout.setError(null);
        binding.cPasswordLayout.setError(null);
        binding.dateBirthLayout.setError(null);
        binding.phoneNumberLayout.setError(null);

        switch (errorId) {
            case 0:
                break;
            case 1:
                binding.fNameLayout.setError(getString(R.string.fName_required));

                break;
            case 2:
                binding.lNameLayout.setError(getString(R.string.lname_required));

                break;
            case 3:
                binding.teamNameLayout.setError(getString(R.string.teamName_required));

                break;
            case 4:
                binding.emailLayout.setError(getString(R.string.email_required));
                break;
            case 5:
                binding.passwordLayout.setError(getString(R.string.password_required));

                break;
            case 6:
                binding.cPasswordLayout.setError(getString(R.string.c_password_required));

                break;
            case 7:
                binding.dateBirthLayout.setError(getString(R.string.date_required));

                break;
            case 8:
                binding.phoneNumberLayout.setError(getString(R.string.phone_required));
                break;
            case 9:
                Toast.makeText(getActivity(), getString(R.string.choose_governorate), Toast.LENGTH_SHORT).show();
                break;
            case 10 :
                Toast.makeText(getActivity(), getString(R.string.select_image), Toast.LENGTH_SHORT).show();
                break;
            case 11:
                binding.passwordLayout.setError(getString(R.string.pass_less_8));
                break;
            case 12:
                binding.cPasswordLayout.setError(getString(R.string.password_not_match));
            case 13:
                binding.phoneNumberLayout.setError(getString(R.string.phone_incorrect));
                break;
        }

        return true;
    }

    // pass data to Register view model . . . . ;
    private UserRegister passRegisterInformation(){
        userrInfo.setFname(binding.fName.getText().toString());
        userrInfo.setLname(binding.lName.getText().toString());
        userrInfo.setEmail(binding.email.getText().toString());
        userrInfo.setTeam_name(binding.teamName.getText().toString());
        userrInfo.setPassword(binding.password.getText().toString());
        userrInfo.setC_password(binding.cPassword.getText().toString());
        userrInfo.setGovernorate(binding.govSpinner.getSelectedItem().toString());
        userrInfo.setPhone_number(convertPhone());
        if(binding.male.isChecked()){
            userrInfo.setSex(getString(R.string.male));
        }else {
            userrInfo.setSex(getString(R.string.female));
        }
        return userrInfo;
            }
    // convert phone text to string .. . :
    public String convertPhone(){
        if(TextUtils.isEmpty(binding.phoneNumber.getText()) || binding.phoneNumber.getText().toString() == null){
            return "";
        }else {
            return binding.phoneNumber.getText().toString();
        }
    }

    @Override
    public void onClick(final View view) {
      int id = view.getId();
      switch (id){
          // when click on image user . . . ;
          case R.id.image_user:
              Intent intent = new Intent(Intent.ACTION_PICK);
              intent.setType("image/*");
              startActivityForResult(intent, ContractClass.IMAGE_REQUESTCODE);
              break;
              // when click on edit birth text . .. ;
          case R.id.date_birth:
              FragmentManager fragmentManager = getFragmentManager() ;
              BirthDialogFragment dialogFragment = BirthDialogFragment.getInstance(binding.dateBirth.getText().toString());
              dialogFragment.setTargetFragment(RegisterFragment.this, 2222);
              dialogFragment.show(fragmentManager,"dialog_birth");
              break;
              // when click in register button . .  ;
          case R.id.register_btn:
              registerViewModel.setUserInformation(passRegisterInformation());
              registerViewModel.getUserInformation();
              int errorId = registerViewModel.validateViews();
              if(errorId != 0){
              validateViewsEmpty(errorId);
              }else {
                  binding.registerRelativeProgress.setVisibility(View.VISIBLE);

                  registerViewModel.passDataToTheDatabase().enqueue(new Callback<LoginCallback>() {
                     @Override
                     public void onResponse(Call<LoginCallback> call, Response<LoginCallback> response) {
String a = "";
                         String status = response.body().getStatus();
                         final String user_id = response.body().getId();
                         switch (status){
                             case "success":
                                 Snackbar snackbar = Snackbar
                                         .make(view,getString(R.string.login_successful),Snackbar.LENGTH_SHORT)
                                         .addCallback(new Snackbar.Callback(){
                                             @Override
                                             public void onShown(Snackbar sb) {
                                                 Preferences.setPreferences(getActivity(),ContractClass.USER_FILE,ContractClass.USER_ID_KEY,user_id);
                                                 binding.registerRelativeProgress.setVisibility(View.GONE);
                                             }

                                             @Override
                                             public void onDismissed(Snackbar transientBottomBar, int event) {
                                                 startActivity(new Intent(getActivity(),BasicActvity.class));
                                                 getActivity().finish();
                                             }
                                         });
                                 snackbar.show();

                                 break;
                             case "failure":
                                 Toast.makeText(view.getContext(),response.body().getErrorMsg(), Toast.LENGTH_SHORT).show();
                               //  binding.emailLayout.setError(getString(R.string.email_exist));
                                 binding.registerRelativeProgress.setVisibility(View.GONE);

                                 break;
                                 default:
                                     Toast.makeText(getActivity(),getString(R.string.something_wrong),Toast.LENGTH_LONG).show();
                                     binding.registerRelativeProgress.setVisibility(View.GONE);

                                     break;
                         }

                     }

                     @Override
                     public void onFailure(Call<LoginCallback> call, Throwable t) {
                         Log.i("Registerfailure",t.getMessage());
                         binding.registerRelativeProgress.setVisibility(View.GONE);

                     }
                 });
              }

              break;
          case R.id.signin_label:
              FragmentManager manager = getFragmentManager() ;
              Fragment registerFragment = LoginFragment.getLoginFragment();

              manager.beginTransaction()
                      .replace(R.id.login_container,registerFragment)
                      .commit();
              break;


      }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // when fetch image from gallery . . . ;
        if(requestCode == ContractClass.IMAGE_REQUESTCODE && resultCode == RESULT_OK && data != null){
         Uri imageDir = data.getData();
         binding.imageUser.setImageURI(imageDir);

            InputStream imageStream = null;
            try {
                 imageStream = getActivity().getContentResolver().openInputStream(imageDir);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            String encodedImage = encodeImage(selectedImage);
            userrInfo.setImage_base64(encodedImage);
            registerViewModel.setBase64(encodedImage);
            binding.imageUser.setImageBitmap(selectedImage);

        }else if(requestCode == 2222){
            String date = (String) data.getSerializableExtra(ContractClass.EXTRA_DATE);
            binding.dateBirth.setText(date);
            userrInfo.setDate_birth(date);
        }

    }

    // encode image to base 64 . . . ;
    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    // encode image from base64 to bitmap .. .
    private Bitmap encodeBase64(String base64){
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return  BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    }
}
