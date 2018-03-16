package com.mukess.android.pepper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ABC";
    private static final int REQUEST_INVITE = 1;
    Button signoutbtn, creditsbtn, share;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        //Sign out Button
        signoutbtn = rootView.findViewById(R.id.button8);
        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            }
                        });
            }
        });

        // Get User Details
        textView = rootView.findViewById(R.id.textView);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
            if (user.getDisplayName() != null)
                textView.setText(user.getDisplayName());

        //Displaying Credits
        creditsbtn = rootView.findViewById(R.id.button9);
        creditsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Credits.class));
            }
        });

        //share
        share = rootView.findViewById(R.id.button10);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInviteClicked();
            }
        });

        return rootView;
    }

    public void onInviteClicked() {
        String invitation_title = "Share with friends";
        String invitation_message = "Canteen Menu on your phone";

        Intent intent = new AppInviteInvitation.IntentBuilder(invitation_title)
                .setMessage(invitation_message)
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
            }
        }
    }
}