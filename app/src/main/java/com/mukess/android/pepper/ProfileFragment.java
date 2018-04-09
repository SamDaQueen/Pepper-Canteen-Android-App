package com.mukess.android.pepper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ABC";
    private static final int REQUEST_INVITE = 1;
    private static final int PICK_IMAGE = 3;
    private static int count = 0;
    private static boolean flag = true;
    myDBHandler dbHandler;
    private TextView textView;
    private ImageView imageView;
    private TextView hint;
    private Button signoutbtn, creditsbtn, share, order;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        dbHandler = new myDBHandler(getContext(), null, null, 1);

        //Sign out Button
        signoutbtn = rootView.findViewById(R.id.button8);
        signOutFunction();

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

        //order history
        order = rootView.findViewById(R.id.button3);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer history = dbHandler.databaseToString();
                String send = history.toString();
                Intent intent = new Intent(getActivity(), OrderHistory.class);
                intent.putExtra("OrderHistory", send);
                startActivity(intent);
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

        //image
        if (flag) {
            imageView = rootView.findViewById(R.id.imageView);
            hint = rootView.findViewById(R.id.hint);
            setImageView();
        }
        return rootView;
    }

    public void onInviteClicked() {
        String invitation_title = "Share Pepper";
        String invitation_message = "Download Pepper - The Canteen Menu App for MPSTME";
        String invitation_deep_link = "https://play.google.com/store/apps/details?id=com.mukess.android.pepper";
        Intent intent = new AppInviteInvitation.IntentBuilder(invitation_title)
                .setMessage(invitation_message)
                .setDeepLink(Uri.parse(invitation_deep_link))
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
                Toast.makeText(getActivity(), "Invitation not sent", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (requestCode == PICK_IMAGE) {
                //Pick image from gallery
                if (resultCode == RESULT_OK) try {
                    final Uri imageUri = data.getData();
                    assert imageUri != null;
                    final InputStream imageStream = Objects.requireNonNull(getActivity()).getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    hint.setVisibility(View.INVISIBLE);
                    imageView.setImageBitmap(selectedImage);
                    flag = false;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            } else
                Toast.makeText(getActivity(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void signOutFunction() {
        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(Objects.requireNonNull(getActivity()))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            }
                        });
            }
        });
    }

    public void setImageView() {
        //setting listener on image
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                return false;
            }
        });
    }
}