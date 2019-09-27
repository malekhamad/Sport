package com.geniusmind.sport.View;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class BirthDialogFragment extends DialogFragment {
    DatePicker datePicker;

    public static BirthDialogFragment getInstance(String date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("date", date);

        BirthDialogFragment dialogFragment = new BirthDialogFragment();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_birth_dialog, null);
        datePicker = view.findViewById(R.id.date_picker_dialog);
        String dateBirth = (String) getArguments().getSerializable("date");




        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_birth)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       String Year =String.valueOf(datePicker.getYear());
                       String Month =String.valueOf(datePicker.getMonth()+1);
                       String day =String.valueOf(datePicker.getDayOfMonth());
                       String date = day+"/"+Month+"/"+Year;

                        sendResult(Activity.RESULT_OK,date);
                    }
                })
                .create();

    }


    private void sendResult(int result_code ,String date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(ContractClass.EXTRA_DATE,date);

        getTargetFragment().onActivityResult(getTargetRequestCode(),result_code,intent);
    }


}
