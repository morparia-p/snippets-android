package com.google.firebase.dynamicinvites.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.dynamicinvites.R;
import com.google.firebase.dynamicinvites.presenter.InvitePresenter;

public class AdvancedActivity extends AppCompatActivity implements ShareDialogFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        findViewById(R.id.button_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareDialogFragment.newInstance().show(getSupportFragmentManager(), "dialog");
            }
        });
    }

    @Override
    public void onItemClicked(InvitePresenter presenter) {
        presenter.sendInvite(this);
    }
}
